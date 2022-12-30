package com.example.springbootdemo.mapper;

import com.example.springbootdemo.bean.Userbean;
import com.example.springbootdemo.bean.visitorbean;

public interface visitorMapper {
    visitorbean getInfo(String name, String password);
}
