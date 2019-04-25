package com.martin.springcloud.user.controller;

import com.martin.springcloud.user.feignclient.OrderInterface;
import com.martin.springcloud.user.domain.UserDo;
import com.martin.springcloud.user.feignclient.OrderMustFailedInterface;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private OrderInterface orderInterface;

    @Autowired
    private OrderMustFailedInterface orderMustFailedInterface;

    @GetMapping("/{id}")
    @HystrixCommand(fallbackMethod = "getUserFallback",
    groupKey = "userFallbackGroup",
    commandKey = "userFallbackCommand")
    @ResponseBody
    public UserDo getUser(@PathVariable Long id) {
        UserDo userDo = new UserDo();
        userDo.setId(id);
        userDo.setName("名字");
        return restTemplate.getForObject("http://zengguoqiangorder/order/" + userDo.getId(), UserDo.class);
    }

    /**
     * 失败策略
     * @param id
     * @return
     */
    public UserDo getUserFallback(Long id) {
        UserDo user = new UserDo();
        user.setId(0L);
        user.setName("失败了！");
        return user;
    }

    @GetMapping("/feign/{id}")
    @ResponseBody
    public UserDo getFeignUser(@PathVariable Long id) {
        UserDo userDo = new UserDo();
        userDo.setId(id);
        userDo.setName("名字");
        return orderInterface.getOrder(userDo.getId());
    }

    @GetMapping("/feignmustfailed/{id}")
    @ResponseBody
    public UserDo getFeignFailedUser(@PathVariable Long id) {
        UserDo userDo = new UserDo();
        userDo.setId(id);
        userDo.setName("名字");
        return orderMustFailedInterface.getOrder(userDo.getId());
    }

    @GetMapping(value = "/test")
    public String test() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("zengguoqiangorder");
        System.out.println(serviceInstance.getServiceId() + "-->" + serviceInstance.getHost() + ":" + serviceInstance.getPort());
//        ServiceInstance serviceInstance2 = loadBalancerClient.choose("microservice-springcloud-user2");
//        System.out.println(serviceInstance2.getServiceId() + "-->" + serviceInstance2.getHost() + ":" + serviceInstance2.getPort());
        return "hello,world";
    }
}
