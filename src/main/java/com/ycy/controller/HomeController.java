package com.ycy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class HomeController {
    
    @RequestMapping(value = {"/","index"})
    public String index() {
        return "index";
    }
    
}
