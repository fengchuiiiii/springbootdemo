package com.example.springbootdemo.controller;

import com.example.springbootdemo.bean.Userbean;
import com.example.springbootdemo.bean.flightbean;
import com.example.springbootdemo.bean.visitorbean;
import com.example.springbootdemo.service.UserService;
import com.example.springbootdemo.service.flightService;
import com.example.springbootdemo.service.visitorService;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class LoginController {
    @Resource
    UserService userService;
    @Resource
    visitorService visitorService;
    @Resource
    flightService flightservice;
    @RequestMapping(value="/loginView")
    public String usershow(){return "loginView";}
    @RequestMapping(value="/userlogin",method= RequestMethod.POST)
    public String loginMethod(String name, String password, Model m) throws ParseException {
            Userbean userbean=userService.login(name,password);
            List<flightbean> flights = new ArrayList<>();
            flights=flightservice.list();

        for (com.example.springbootdemo.bean.flightbean flight : flights){

                if (flight==null)
                    break;
                else{

//                    Calendar c = Calendar.getInstance();
//                    c.setTime(flight.getPlanstarttime());
//                    c.add(Calendar.HOUR_OF_DAY, -8);
//                    flight.setPlanstarttime(c.getTime());
//                    c.setTime(flight.getPlanarrivetime());
//                    c.add(Calendar.HOUR_OF_DAY, -8);
//                    flight.setPlanarrivetime(c.getTime());
//                    if(flight.getStarttime()!=null){
//                        c.setTime(flight.getStarttime());
//                        c.add(Calendar.HOUR_OF_DAY, -8);
//                        flight.setStarttime(c.getTime());
//                    }
//                    if(flight.getArrivetime()!=null){
//                        c.setTime(flight.getArrivetime());
//                        c.add(Calendar.HOUR_OF_DAY, -8);
//                        flight.setArrivetime(c.getTime());}

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date planstartdate=simpleDateFormat.parse(flight.getPlanstarttime());

                    Date planarrivedate=simpleDateFormat.parse(flight.getPlanarrivetime());

                    Date now = new Date();
                    long board=-1;
                    if(now.getTime()<=planstartdate.getTime()) {
                        board = (planstartdate.getTime() - now.getTime()) / 1000 / 60;
                        if (board <= 30 && board > 0) {
                            flight.setState("正在登机");
                        }
                        if (board == 0) {
                            flight.setState("结束登机");
                        }
                    }
                    if(flight.getStarttime()!=null)
                    {
                        Date startdate=simpleDateFormat.parse(flight.getStarttime());
                        flight.setSd((startdate.getTime()-planstartdate.getTime())/1000/60);
                        if(flight.getSd()<25&&flight.getSd()>0)
                        {
                            flight.setState("正常起飞");
                        }
                        if(flight.getSd()>=25){
                            flight.setState("延迟起飞");
                        }
                    }
                    if(flight.getArrivetime()!=null)
                    {
                        Date arrivedate=simpleDateFormat.parse(flight.getArrivetime());
                        flight.setAd((arrivedate.getTime()-planarrivedate.getTime())/1000/60);
                        if(flight.getAd()<15&&flight.getSd()>0)
                        {
                            flight.setState("准时到达");
                        }
                        if(flight.getSd()>=15){
                            flight.setState("航班延误");
                        }
                    }

                }


            }
            m.addAttribute("flights",flights);



        if(userbean!=null)
            return "flight";
        else
            return "error";

    }
    @RequestMapping(value="/visitorloginView")
    public String visitorshow(){return "visitorloginView";}
    @RequestMapping(value="/visitorlogin",method= RequestMethod.POST)
    public String visitorloginMethod(String name, String password, Model m) throws ParseException {
        visitorbean visitorbean= visitorService.login(name,password);
        List<flightbean> flights = new ArrayList<>();
        flights=flightservice.list();

        for (com.example.springbootdemo.bean.flightbean flight : flights){

            if (flight==null)
                break;
            else{

//                    Calendar c = Calendar.getInstance();
//                    c.setTime(flight.getPlanstarttime());
//                    c.add(Calendar.HOUR_OF_DAY, -8);
//                    flight.setPlanstarttime(c.getTime());
//                    c.setTime(flight.getPlanarrivetime());
//                    c.add(Calendar.HOUR_OF_DAY, -8);
//                    flight.setPlanarrivetime(c.getTime());
//                    if(flight.getStarttime()!=null){
//                        c.setTime(flight.getStarttime());
//                        c.add(Calendar.HOUR_OF_DAY, -8);
//                        flight.setStarttime(c.getTime());
//                    }
//                    if(flight.getArrivetime()!=null){
//                        c.setTime(flight.getArrivetime());
//                        c.add(Calendar.HOUR_OF_DAY, -8);
//                        flight.setArrivetime(c.getTime());}

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date planstartdate=simpleDateFormat.parse(flight.getPlanstarttime());

                Date planarrivedate=simpleDateFormat.parse(flight.getPlanarrivetime());

                Date now = new Date();
                long board=-1;
                if(now.getTime()<=planstartdate.getTime()) {
                    board = (planstartdate.getTime() - now.getTime()) / 1000 / 60;
                    if (board <= 30 && board > 0) {
                        flight.setState("正在登机");
                    }
                    if (board == 0) {
                        flight.setState("结束登机");
                    }
                }
                if(flight.getStarttime()!=null)
                {
                    Date startdate=simpleDateFormat.parse(flight.getStarttime());
                    flight.setSd((startdate.getTime()-planstartdate.getTime())/1000/60);
                    if(flight.getSd()<25&&flight.getSd()>0)
                    {
                        flight.setState("正常起飞");
                    }
                    if(flight.getSd()>=25){
                        flight.setState("延迟起飞");
                    }
                }
                if(flight.getArrivetime()!=null)
                {
                    Date arrivedate=simpleDateFormat.parse(flight.getArrivetime());
                    flight.setAd((arrivedate.getTime()-planarrivedate.getTime())/1000/60);
                    if(flight.getAd()<15&&flight.getSd()>0)
                    {
                        flight.setState("准时到达");
                    }
                    if(flight.getSd()>=15){
                        flight.setState("航班延误");
                    }
                }

            }


        }
        m.addAttribute("flights",flights);



        if(visitorbean!=null)
            return "visitorflight";
        else
            return "error";

    }

}
