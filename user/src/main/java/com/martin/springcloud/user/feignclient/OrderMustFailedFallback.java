package com.martin.springcloud.user.feignclient;

import com.martin.springcloud.user.domain.UserDo;
import org.springframework.stereotype.Component;

/**
 * @author: zengguoqiang
 * @date: 创建时间：2019/4/25 14:28
 * @description: feign 运用 hystrix
 */
@Component
public class OrderMustFailedFallback implements OrderMustFailedInterface {

    @Override
    public UserDo getOrder(Long userId) {
        return null;
    }

}
