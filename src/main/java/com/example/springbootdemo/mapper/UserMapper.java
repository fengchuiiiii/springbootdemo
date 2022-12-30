package com.example.springbootdemo.mapper;
import com.example.springbootdemo.bean.Userbean;
public interface UserMapper {
    Userbean getInfo(String name,String password);
}
