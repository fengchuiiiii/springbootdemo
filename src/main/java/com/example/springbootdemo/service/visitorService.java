package com.example.springbootdemo.service;

import com.example.springbootdemo.bean.visitorbean;

public interface visitorService {
    visitorbean login(String name, String password);
}
