package com.example.springbootdemo.servicelmpl;
import com.example.springbootdemo.service.visitorService;

import com.example.springbootdemo.bean.visitorbean;

import com.example.springbootdemo.mapper.visitorMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class visitorServicelmpl implements visitorService{
    @Resource
    private visitorMapper visitormapper;

    public visitorbean login(String name, String password){
        return  visitormapper.getInfo(name,password);
    }
}

