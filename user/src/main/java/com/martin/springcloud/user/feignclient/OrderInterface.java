package com.martin.springcloud.user.feignclient;

import com.martin.springcloud.user.domain.UserDo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "zengguoqiangorder",fallback = OrderFallback.class)
public interface OrderInterface {

    @RequestMapping(value = "/order/{userId}", method = RequestMethod.GET)
    UserDo getOrder(@PathVariable("userId") Long userId);

}
