package com.zheliban.miaosha.controller;

import com.zheliban.miaosha.domain.MiaoshaUser;
import com.zheliban.miaosha.redis.RedisService;
import com.zheliban.miaosha.service.MiaoshaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    MiaoshaUserService userService;
    @Autowired
    RedisService redisService;

    @RequestMapping("/to_list")
    /*
     * do_login的响应体设置了cookie，存到了浏览器里，to_list请求的时候带上了cookie
     * 请求的时候会带上cookie，可以取值
     * 有些手机客户端并不会把token放进cookie里传给我们的服务端，而是放到参数里面传递，为了兼容这种情况从Request里再取一下这个cookie
     */
    public String toLogin(Model model, MiaoshaUser user) {//
        model.addAttribute("user", user);
        return "goods_list"; //跳转到商品列表
    }
}
