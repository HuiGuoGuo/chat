package com.test.chat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by 王辉 on 2016/8/29.
 */
@Controller
public class IndexController {
    private static final Logger Log = LoggerFactory.getLogger(IndexController.class);
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        Log.info("index");
        System.out.println("index");
        return "index";
    }
}
