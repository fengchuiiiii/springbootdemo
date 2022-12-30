package com.example.springbootdemo.service;
import com.example.springbootdemo.bean.Userbean;
public interface UserService {
    Userbean login(String name,String password);
}
