package com.martin.springcloud.user.controller;

import com.martin.springcloud.user.domain.UserDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{id}")
    @ResponseBody
    public UserDo getUser(@PathVariable Long id) {
        UserDo userDo = new UserDo();
        userDo.setId(id);
        userDo.setName("名字");
        return restTemplate.getForObject("http://zengguoqiangorder/order/" + userDo.getId(), UserDo.class);
    }

}
