package com.example.springbootdemo.controller;
import com.example.springbootdemo.bean.flightbean;
import com.example.springbootdemo.mapper.flightMapper;
import com.example.springbootdemo.service.flightService;
import com.example.springbootdemo.service.visitorService;
import org.apache.ibatis.javassist.expr.NewArray;
import org.apache.ibatis.jdbc.Null;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;

@Controller
public class flightController {

    @Resource
    flightService flightservice;
    @Resource
    visitorService visitorService;
    @RequestMapping (value = "/toUpdate")
    public String showup(int id ,Model m){
        flightbean flight=new flightbean();
        flight=flightservice.getInformation(id);
        m.addAttribute("flight1",flight);
        return "update";
    }
    @RequestMapping(value = "/Update",method = RequestMethod.POST)
    public String flightup(flightbean flight1){
        System.out.println(flight1.getNum());
        flightservice.update(flight1);
        return "success";

    }
    @RequestMapping (value = "/Delete")
    public String flightdelete(int id,Model m) throws ParseException {
        int a = flightservice.delete(id);

        return "success";
    }
    @RequestMapping (value = "/Insert")
    public String show(){return "insert";}
    @RequestMapping (value = "/Insert",method = RequestMethod.POST)
    public String flightinsert(int id,String num,String company, String origin, String destination, String stops,
                               String planstarttime, String starttime, String planarrivetime,
                               String arrivetime, String terminal, String boardinggates, String checkin,
                               String state)
    {
        flightbean flight=new flightbean();
        flight.setId(id);
        flight.setNum(num);
        flight.setCompany(company);
        flight.setOrigin(origin);
        flight.setDestination(destination);
        flight.setStops(stops);
        flight.setPlanstarttime(planstarttime);
        if(starttime!=null){
            flight.setStarttime(starttime);
        }
        flight.setPlanarrivetime(planarrivetime);
        if(arrivetime!=null){
            flight.setArrivetime(arrivetime);
        }
        flight.setTerminal(terminal);
        flight.setBoardinggates(boardinggates);
        flight.setCheckin(checkin);
        flight.setState(state);
        int i=flightservice.insert(flight);
        return "success";
    }
    @RequestMapping (value = "/searchs")
    public String showsearch(){return "flight";}
    @RequestMapping (value = "/searchs",method = RequestMethod.POST)
    public String flightsearch(String num, Model m1) throws ParseException {

        List<flightbean> flights = new ArrayList<>();
        flights=flightservice.getnum(num);//Integer.toString(i));
        for (com.example.springbootdemo.bean.flightbean flight : flights) {
//            flightbean flight = new flightbean();
//            flight = flightbean;
//            Calendar c = Calendar.getInstance();
//            c.setTime(flight.getPlanstarttime());
//            c.add(Calendar.HOUR_OF_DAY, -8);
//            flight.setPlanstarttime(c.getTime());
//            c.setTime(flight.getPlanarrivetime());
//            c.add(Calendar.HOUR_OF_DAY, -8);
//            flight.setPlanarrivetime(c.getTime());
//            if (flight.getStarttime() != null) {
//                c.setTime(flight.getStarttime());
//                c.add(Calendar.HOUR_OF_DAY, -8);
//                flight.setStarttime(c.getTime());
//            }
//            if (flight.getArrivetime() != null) {
//                c.setTime(flight.getArrivetime());
//                c.add(Calendar.HOUR_OF_DAY, -8);
//                flight.setArrivetime(c.getTime());
//            }
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
    ;

        m1.addAttribute("flights",flights);
        return "flight";

    }
    @RequestMapping(value = "back")
    public String back(Model m) throws ParseException {
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
        return "flight";
    }
    @RequestMapping(value = "vback")
    public String vback(Model m) throws ParseException {
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
        return "visitorflight";
    }
    @RequestMapping (value = "/vsearchs")
    public String showvsearch(){return "visitorflight";}
    @RequestMapping (value = "/vsearchs",method = RequestMethod.POST)
    public String vflightsearch(String num, Model m1) throws ParseException {

        List<flightbean> flights = new ArrayList<>();
        flights=flightservice.getnum(num);//Integer.toString(i));
        for (com.example.springbootdemo.bean.flightbean flight : flights) {
//            flightbean flight = new flightbean();
//            flight = flightbean;
//            Calendar c = Calendar.getInstance();
//            c.setTime(flight.getPlanstarttime());
//            c.add(Calendar.HOUR_OF_DAY, -8);
//            flight.setPlanstarttime(c.getTime());
//            c.setTime(flight.getPlanarrivetime());
//            c.add(Calendar.HOUR_OF_DAY, -8);
//            flight.setPlanarrivetime(c.getTime());
//            if (flight.getStarttime() != null) {
//                c.setTime(flight.getStarttime());
//                c.add(Calendar.HOUR_OF_DAY, -8);
//                flight.setStarttime(c.getTime());
//            }
//            if (flight.getArrivetime() != null) {
//                c.setTime(flight.getArrivetime());
//                c.add(Calendar.HOUR_OF_DAY, -8);
//                flight.setArrivetime(c.getTime());
//            }
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
        ;

        m1.addAttribute("flights",flights);
        return "search";

    }

}
