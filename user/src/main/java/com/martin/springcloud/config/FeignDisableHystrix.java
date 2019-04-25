package com.martin.springcloud.config;

import feign.Feign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author: zengguoqiang
 * @date: 创建时间：2019/4/25 15:16
 * @description: feign禁用hystrix
 */
@Configuration
public class FeignDisableHystrix {

    //Configuration2里面加上这个就禁用了UserFeignClient2的Hystrix
    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder() {
        //feignBuilder方法默认返回HystrixFeign.Builder也就是说Feign默认支持Hystrix
        //现在改成Feign.Builder就禁用了Hystrix的支持
        return Feign.builder();
    }
}
