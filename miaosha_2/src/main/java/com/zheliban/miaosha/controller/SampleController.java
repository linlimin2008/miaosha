package com.zheliban.miaosha.controller;

import com.zheliban.miaosha.domain.User;
import com.zheliban.miaosha.rabbitmq.MQSender;
import com.zheliban.miaosha.redis.RedisService;
import com.zheliban.miaosha.redis.UserKey;
import com.zheliban.miaosha.result.CodeMsg;
import com.zheliban.miaosha.result.Result;
import com.zheliban.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo")
public class SampleController {

    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;
    @Autowired
    MQSender sender;

    @RequestMapping("/mq/header")
    @ResponseBody
    public  Result<String> header() {
        sender.sendHeader("hello 0cm header!");
        return Result.success("hello world");
    }

    @RequestMapping("/mq/fanout")
    @ResponseBody
    public  Result<String> fanout() {
        sender.sendFanout("hello 0cm fanout!");
        return Result.success("hello world");
    }

    @RequestMapping("/mq/topic")
    @ResponseBody
    public  Result<String> topic() {
        sender.sendTopic("hello 0cm topic!");
        return Result.success("hello world");
    }

    @RequestMapping("/mq")
    @ResponseBody
    public  Result<String> mq() {
        sender.send("hello 0cm!");
        return Result.success("hello world");
    }

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "hello aa";
    }
    //controller层的两种功能：1、rest api json输出 		 2、页面

    @RequestMapping("/hello")
    @ResponseBody
    public Result<String> hello() {
        return Result.success("hello.word");
    }

    @RequestMapping("/helloError")
    @ResponseBody
    public Result<String> helloError() {
        return Result.error(CodeMsg.SERVER_ERROR);
    }

    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model) {
        model.addAttribute("name", "三毛姑卡");
        return "hello";
    }

    @RequestMapping("/db/get")
    @ResponseBody
    public Result<User> dbGet() {
        User user = userService.getById(1);
        return Result.success(user);
    }

    @RequestMapping("/db/tx")
    @ResponseBody
    public Result<Boolean> dbTx() {
        userService.tx();
        return Result.success(true);
    }

    @RequestMapping("/redis/get")        //读
    @ResponseBody
    public Result<User> redisGet() {
        User user = redisService.get(UserKey.getById, "" + 1, User.class);//根据键获取值，值以User的类型返回
        return Result.success(user);
    }

    @RequestMapping("/redis/set")        //写
    @ResponseBody
    public Result<Boolean> rediSet() {
        User user = new User();
        user.setId(1);
        user.setName("11111");
        redisService.set(UserKey.getById, "" + 1, user);//将键值对写进去，值是User类型的  UserKey:id1
        return Result.success(true);
    }


}
