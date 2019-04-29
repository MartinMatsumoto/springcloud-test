package com.martin.springcloud.config;

import feign.Request;
import feign.Retryer;
import feign.hystrix.HystrixFeign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author: zengguoqiang
 * @date: 创建时间：2019/4/25 15:16
 * @description: feign禁用hystrix
 */
@Configuration
public class FeignEnableHystrix {

    public static int connectTimeOutMillis = 12000;//超时时间
    public static int readTimeOutMillis = 12000;

    //Configuration2里面加上这个就禁用了UserFeignClient2的Hystrix
    @Bean
    @Scope("prototype")
    public HystrixFeign.Builder feignBuilder() {
        //feignBuilder方法默认返回HystrixFeign.Builder也就是说Feign默认支持Hystrix
        //现在改成Feign.Builder就禁用了Hystrix的支持
        return HystrixFeign.builder();
    }

    @Bean
    public Request.Options options() {
        return new Request.Options(connectTimeOutMillis, readTimeOutMillis);
    }

    @Bean
    public Retryer feignRetryer() {
//        return new Retryer.Default(); //默认
//        return Retryer.NEVER_RETRY; //取消重试
        return new Retryer.Default(100, 1000, 4);
    }
}
