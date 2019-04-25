package com.martin.springcloud.user.feignclient;

import com.martin.springcloud.user.domain.UserDo;
import org.springframework.stereotype.Component;

/**
 * @author: zengguoqiang
 * @date: 创建时间：2019/4/25 14:28
 * @description: feign 运用 hystrix
 */
@Component
public class OrderFallback implements OrderInterface {
    @Override
    public UserDo getOrder(Long userId) {
        UserDo userDo = new UserDo();
        userDo.setId(100L);
        userDo.setName("feign失败后回传");
        return userDo;
    }
}
