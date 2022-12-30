package com.example.springbootdemo.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class flightbean {
    private int id;
    private String num;
    private String company;
    private String origin;
    private String destination;
    private String stops;

    private String planstarttime;
    private String starttime;
    private long sd;
    private String planarrivetime;

    private String arrivetime;
    private long ad;
    private String terminal;
    private String boardinggates;
    private String checkin;
    private String state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlanstarttime() {
        return planstarttime;
    }

    public void setPlanstarttime(String planstarttime) {
        this.planstarttime = planstarttime;
    }

    public long getSd() {
        return sd;
    }

    public void setSd(long sd) {
        this.sd = sd;
    }

    public long getAd() {
        return ad;
    }

    public void setAd(long ad) {
        this.ad = ad;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getStops() {
        return stops;
    }

    public void setStops(String stops) {
        this.stops = stops;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getPlanarrivetime() {
        return planarrivetime;
    }

    public void setPlanarrivetime(String planarrivetime) {
        this.planarrivetime = planarrivetime;
    }

    public String getArrivetime() {
        return arrivetime;
    }

    public void setArrivetime(String arrivetime) {
        this.arrivetime = arrivetime;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getBoardinggates() {
        return boardinggates;
    }

    public void setBoardinggates(String boardinggates) {
        this.boardinggates = boardinggates;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
