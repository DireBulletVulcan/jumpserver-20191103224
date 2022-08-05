package com.ycy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class HomeController {
    
    @RequestMapping("index")
    public String index(ModelMap map) {
        String userName = "xingjian";
        
        // 将Java中的变量传递给视图模板引擎
        map.put("userName", userName);
        return "index";
    }
    
}
