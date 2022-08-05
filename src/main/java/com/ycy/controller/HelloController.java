package com.ycy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/hello")
public class HelloController {
    
    public String fun() {
        return "Hello";
    }
    
    @RequestMapping("/test")
    public String test(){
        
        return "test";
    }
    
}