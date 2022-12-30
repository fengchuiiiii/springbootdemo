package com.example.springbootdemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.springbootdemo.bean.Userbean;
import com.example.springbootdemo.service.UserService;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import  javax.annotation.Resource;
@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootdemoApplicationTests {
    @Resource
    UserService userService;
    @Test
    void contextLoads() {
        Userbean userbean= userService.login("Mike","123");
        System.out.println(("user id is:"));
        System.out.println(userbean.getId());
    }

}
