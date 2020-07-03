package com.zheliban.miaosha.controller;

import com.zheliban.miaosha.redis.RedisService;
import com.zheliban.miaosha.result.Result;
import com.zheliban.miaosha.service.MiaoshaUserService;
import com.zheliban.miaosha.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@Controller
@RequestMapping("/login")
public class LoginController {

    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    MiaoshaUserService userService;

    @Autowired
    RedisService redisService;

    //controller层的两种功能：1、rest api json输出 		 2、页面

    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public Result<Boolean> doLogin(HttpServletResponse response, @Valid LoginVo loginVo) {    //1、在参数前面打标签，意义在哪儿？
        log.info(loginVo.toString());
        userService.login(response, loginVo);//登录MiaoshaUserService，如果出现各种各样的异常GlobalException就向外抛，GlobalExceptionHande拦截异常将异常输出
        return Result.success(true);
    }


}
