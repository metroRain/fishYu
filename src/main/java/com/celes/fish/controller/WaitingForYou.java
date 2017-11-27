package com.celes.fish.controller;

import com.celes.fish.entity.Data;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@RestController
public class WaitingForYou {

    @RequestMapping("/getLove")
    public Data getLove() {
        File love = FileUtils.getFile("/home/love.txt");
        Data data = new Data();
        data.setSuccessful(true);
        if(null == love) {
            // she has not agreed yet
            // we need to wait
            return data;
        }
        // if we reach this point, congratulations
        Properties loveTime = new Properties();
        try {
            loveTime.load(new FileInputStream(love));
        } catch (Exception e) {
            System.out.println("cannot load properties for time");
        }
        data.setYear(getTime(loveTime.getProperty("year"),""));
        data.setMonth(getTime(loveTime.getProperty("month"), ""));
        data.setDay(getTime(loveTime.getProperty("day"), ""));
        data.setHour(getTime(loveTime.getProperty("hour"), ""));
        data.setMinute(getTime(loveTime.getProperty("min"), ""));
        data.setSecond(getTime(loveTime.getProperty("sec"), ""));
        return data;
    }

    @RequestMapping("/setLove")
    public Data setLove() {
        return null;
    }

    private String getTime(String time, String defaultValue) {
        if(null == defaultValue) {
            return "";
        }
        return null == time ? defaultValue : time;
    }
}
