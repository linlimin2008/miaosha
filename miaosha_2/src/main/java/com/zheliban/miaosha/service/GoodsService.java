package com.zheliban.miaosha.service;

import com.zheliban.miaosha.dao.GoodsDao;
import com.zheliban.miaosha.domain.MiaoshaUser;
import com.zheliban.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @功能:
 * @项目名:miaosha_2
 * @作者:0cm
 * @日期:2020/7/87:27 下午
 */
@Service
public class GoodsService {
    @Autowired
    GoodsDao goodsDao;

    public List<GoodsVo> listGoodsVo(){
        return goodsDao.listGoodsVo();
    }

    public GoodsVo getGoodsVoByGoodsId(long goodsId) {
        return goodsDao.getGoodsVoByGoodsId(goodsId);
    }
}
