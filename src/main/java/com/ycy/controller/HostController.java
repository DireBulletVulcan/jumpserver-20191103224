package com.ycy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("host")
public class HostController {
    
    @RequestMapping("list")
    public String list() {
        return "host/list";
    }
    
}
