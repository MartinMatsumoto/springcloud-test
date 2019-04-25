package com.martin.springcloud.user.feignclient;

import com.martin.springcloud.config.FeignDisableHystrix;
import com.martin.springcloud.config.FeignEnableHystrix;
import com.martin.springcloud.user.domain.UserDo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: zengguoqiang
 * @date: 创建时间：2019/4/25 15:18
 * @description: 测试一下 肯定会失败的feign
 */
@FeignClient(value = "zengguoqiangorder", configuration = FeignDisableHystrix.class, fallback = OrderMustFailedFallback.class)
public interface OrderMustFailedInterface {

    @RequestMapping(value = "/???/{userId}", method = RequestMethod.GET)
    UserDo getOrder(@PathVariable("userId") Long userId);

}
