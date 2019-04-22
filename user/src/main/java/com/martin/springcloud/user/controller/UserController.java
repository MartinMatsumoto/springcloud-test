package com.martin.springcloud.user.controller;

import com.martin.springcloud.user.domain.UserDo;
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

    @GetMapping("/{id}")
    @ResponseBody
    public UserDo getUser(@PathVariable Long id) {
        UserDo userDo = new UserDo();
        userDo.setId(id);
        userDo.setName("名字");
        return restTemplate.getForObject("http://zengguoqiangorder/order/" + userDo.getId(), UserDo.class);
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
