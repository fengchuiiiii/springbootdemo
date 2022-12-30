package com.example.springbootdemo.service;

import com.example.springbootdemo.bean.flightbean;

import java.util.List;

public interface flightService {
    flightbean getInformation(int id);
    List<flightbean> list();
    List<flightbean> getnum(String num);
    int insert(flightbean flight);
    int delete(int id);
    void update(flightbean flight);
}
