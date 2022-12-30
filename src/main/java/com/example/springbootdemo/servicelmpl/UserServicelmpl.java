package com.example.springbootdemo.servicelmpl;
import com.example.springbootdemo.bean.Userbean;
import com.example.springbootdemo.mapper.UserMapper;
import com.example.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
@Service
public class UserServicelmpl implements UserService {
    @Resource
    private UserMapper usermapper;
    public Userbean login(String name,String password){
        return  usermapper.getInfo(name,password);
    }

}
