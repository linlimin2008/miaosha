package com.zheliban.miaosha.controller;

import com.zheliban.miaosha.domain.MiaoshaUser;
import com.zheliban.miaosha.redis.RedisService;
import com.zheliban.miaosha.result.Result;
import com.zheliban.miaosha.service.GoodsService;
import com.zheliban.miaosha.service.MiaoshaUserService;
import com.zheliban.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    MiaoshaUserService userService;
    @Autowired
    RedisService redisService;


    @RequestMapping("/info")
    @ResponseBody
    public Result<MiaoshaUser> to_list(Model model, MiaoshaUser user) {//
        model.addAttribute("user", user);
        return Result.success(user);
    }

}
