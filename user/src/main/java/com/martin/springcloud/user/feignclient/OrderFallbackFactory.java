package com.martin.springcloud.user.feignclient;

import com.martin.springcloud.user.domain.UserDo;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author: zengguoqiang
 * @date: 创建时间：2019/4/26 13:37
 * @description: 打印回退原因  可以和OrderFactoryInterface写一个类里面
 */
@Component
public class OrderFallbackFactory implements FallbackFactory<OrderFactoryInterface> {

    @Override
    public OrderFactoryInterface create(Throwable throwable) {
        System.out.println("fallback reason:{}"+ throwable.getMessage());

        return userId -> {
            UserDo userDo = new UserDo();
            userDo.setId(100L);
            userDo.setName("feign失败后工厂回传");
            return userDo;
        };
    }
}