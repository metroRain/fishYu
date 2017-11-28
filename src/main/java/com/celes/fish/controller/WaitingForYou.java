package com.celes.fish.controller;

import com.celes.fish.entity.Data;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Properties;

@RestController
public class WaitingForYou {

    @RequestMapping("/getLove")
    public Data getLove() {
        File love = FileUtils.getFile("/Users/changsu/Documents/love.properties");
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
        Calendar loveDate = Calendar.getInstance();

        boolean isSuccessful = true;

        String YEAR = String.valueOf(loveDate.get(Calendar.YEAR));
        String MONTH = String.valueOf(loveDate.get(Calendar.MONTH) + 1);
        String DAY = String.valueOf(loveDate.get(Calendar.DAY_OF_MONTH));
        String HOUR = String.valueOf(loveDate.get(Calendar.HOUR_OF_DAY));
        String MINUTE = String.valueOf(loveDate.get(Calendar.MINUTE));
        String SECOND = String.valueOf(loveDate.get(Calendar.SECOND));

        // set properties for love time
        Properties loveTime = new Properties();
        loveTime.setProperty("year", YEAR);
        loveTime.setProperty("month", MONTH);
        loveTime.setProperty("day", DAY);
        loveTime.setProperty("hour", HOUR);
        loveTime.setProperty("min", MINUTE);
        loveTime.setProperty("sec", SECOND);

        File loveFile = FileUtils.getFile("/Users/changsu/Documents/love.properties");
        if(loveFile.exists()) {
            // first time it cannot be existed
            loveFile.delete();
        }

        try {
            // create a new one
            loveFile.createNewFile();
            OutputStream os = new FileOutputStream(loveFile);
            loveTime.store(os,"we are starting love");
        } catch (IOException e) {
            // cannot create love file
            System.out.println(" cannot create love properties");
            isSuccessful = false;
        }

        Data result = new Data();
        result.setSuccessful(true);
        if(isSuccessful) {
            result.setYear(getTime(YEAR, ""));
            result.setMonth(getTime(MONTH, ""));
            result.setDay(getTime(DAY, ""));
            result.setHour(getTime(HOUR, ""));
            result.setMinute(getTime(MINUTE, ""));
            result.setSecond(getTime(SECOND, ""));
        }

        return result;
    }

    private String getTime(String time, String defaultValue) {
        if(null == defaultValue) {
            return "";
        }
        return null == time ? defaultValue : time;
    }
}
