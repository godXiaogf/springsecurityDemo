package com.xiao.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String mainPage(){
        System.out.println("/login....");
        return "normal/success";
    }

    @RequestMapping("/fail")
    public String failPage(){
        return "fail";
    }

    @RequestMapping("/add")
    public String addPage(){
        return "normal/resadd";
    }

    @RequestMapping("/del")
    public String delPage(){
        return "normal/resdel";
    }

    @RequestMapping("/update")
    public String updatePage(){
        return "normal/resupdate";
    }

    @RequestMapping("/list")
    public String listPage(){
        return "normal/reslist";
    }

    @RequestMapping("/403") //对应 ErrorPageConfig中的路径
    public String forbidden() {
        return "403";
    }
}
