package com.zheliban.miaosha.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @功能:
 * @项目名:miaosha_2
 * @作者:0cm
 * @日期:2020/6/232:47 下午
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    UserArgumentResolver userArgumentResolver;
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers){
        resolvers.add(userArgumentResolver);
    }
}
