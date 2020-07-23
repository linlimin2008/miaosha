package com.zheliban.miaosha.controller;

import com.zheliban.miaosha.domain.MiaoshaOrder;
import com.zheliban.miaosha.domain.MiaoshaUser;
import com.zheliban.miaosha.domain.OrderInfo;
import com.zheliban.miaosha.rabbitmq.MQSender;
import com.zheliban.miaosha.rabbitmq.MiaoshaMessage;
import com.zheliban.miaosha.redis.GoodsKey;
import com.zheliban.miaosha.redis.RedisService;
import com.zheliban.miaosha.result.CodeMsg;
import com.zheliban.miaosha.result.Result;
import com.zheliban.miaosha.service.GoodsService;
import com.zheliban.miaosha.service.MiaoshaService;
import com.zheliban.miaosha.service.MiaoshaUserService;
import com.zheliban.miaosha.service.OrderService;
import com.zheliban.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @功能:
 * @项目名:miaosha_2
 * @作者:0cm
 * @日期:2020/7/89:41 下午
 */
@Controller
@RequestMapping("/miaosha")
public class MiaoshaController implements InitializingBean {
    @Autowired
    MiaoshaUserService userService;
    @Autowired
    RedisService redisService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    OrderService orderService;
    @Autowired
    MiaoshaService miaoshaService;
    @Autowired
    MQSender mqSender;

    private Map<Long,Boolean> localOverMap = new HashMap<Long,Boolean>();

    /**
     * 系统初始化
     * @throws Exception
     */
    public void afterPropertiesSet() throws Exception {
        List<GoodsVo> goodsVoList = goodsService.listGoodsVo();
        if (goodsVoList !=null){
            for (GoodsVo vo:goodsVoList) {
                redisService.set(GoodsKey.getMiaoshaGoodsStock,""+vo.getId(),vo.getStockCount());
                localOverMap.put(vo.getId(),false);
            }
        }

    }

    @RequestMapping(value = "/do_miaosha",method = RequestMethod.POST)
    @ResponseBody
    public Result<Integer> miaosha(Model model, MiaoshaUser user, @RequestParam("goodsId")long goodsId) {//
        model.addAttribute("user", user);
        if (user == null){
            return Result.error(CodeMsg.SESION_ERROR);
        }
        if (localOverMap.get(goodsId)){
            return Result.error(CodeMsg.MIAO_SHA_OVER);
        }
        //将商品数量加载到缓存中
        long stock = redisService.decr(GoodsKey.getMiaoshaGoodsStock,""+goodsId);
        //预减库存
        if (stock<0){
            localOverMap.put(goodsId,true);
            return Result.error(CodeMsg.MIAO_SHA_OVER);
        }
        //判断是否已经秒杀到了
        MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(),goodsId);
        if (order!=null){
            return Result.error(CodeMsg.REPEATE_MIAOSHA);
        }
        //入队
        MiaoshaMessage miaoshaMessage = new MiaoshaMessage();
        miaoshaMessage.setUser(user);
        miaoshaMessage.setGoodsId(goodsId);
        mqSender.sendMiaoshaMsg(miaoshaMessage);
        return Result.success(0);//排队中

        /**
        //判断库存
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        int stock = goods.getStockCount();
        if (stock<=0){
            return Result.error(CodeMsg.MIAO_SHA_OVER);
        }
        //判断是否已经秒杀到了
        MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(),goodsId);
        if (order!=null){
            return Result.error(CodeMsg.REPEATE_MIAOSHA);
        }
        //减库存 下订单 写入秒杀订单
        OrderInfo orderInfo = miaoshaService.miaosha(user,goods);

         */

//        return Result.success(orderInfo);
    }

    /**
     * orderId :成功
     * -1：秒杀失败
     * 0 ：排队中
     * @param model
     * @param user
     * @param goodsId
     * @return
     */
    @RequestMapping(value = "/result",method = RequestMethod.GET)
    @ResponseBody
    public Result<Long> miaoshaResult(Model model, MiaoshaUser user, @RequestParam("goodsId")long goodsId){
        model.addAttribute("user", user);
        if (user == null){
            return Result.error(CodeMsg.SESION_ERROR);
        }
        long result =  miaoshaService.getMiaoshaResult(user.getId(),goodsId);
        return Result.success(result);
    }

}
