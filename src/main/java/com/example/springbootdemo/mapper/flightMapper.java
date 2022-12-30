package com.example.springbootdemo.mapper;
import com.example.springbootdemo.bean.flightbean;
import java.util.List;
public interface flightMapper {
    flightbean getInfo(int id);
    List<flightbean> list();
    List<flightbean> select(String num);
    int insert(flightbean flight);
    int delete(int id);
    void update(flightbean flight);
}
