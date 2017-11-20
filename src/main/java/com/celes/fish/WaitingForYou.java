package com.celes.fish;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class WaitingForYou {

    @RequestMapping("/getdata")
    public Data get() {
        Data data = new Data();
        data.setSuccessful(true);
        return data;
    }
}
