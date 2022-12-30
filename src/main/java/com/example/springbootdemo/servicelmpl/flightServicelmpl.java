package com.example.springbootdemo.servicelmpl;

import com.example.springbootdemo.bean.flightbean;
import com.example.springbootdemo.mapper.UserMapper;
import com.example.springbootdemo.mapper.flightMapper;
import com.example.springbootdemo.service.flightService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class flightServicelmpl implements flightService{
    @Resource
    private flightMapper flightmapper;
    public flightbean getInformation(int id){
        return  flightmapper.getInfo(id);
    }
    public List<flightbean> list(){return flightmapper.list();}

    public List<flightbean> getnum(String num){return flightmapper.select(num);}//通过航班号查询飞机信息
    public int insert(flightbean flight){
        return  flightmapper.insert(flight);
    }
    public int delete(int id){return flightmapper.delete(id);}
    public void update(flightbean flight){
        flightmapper.update(flight);
    }
}
