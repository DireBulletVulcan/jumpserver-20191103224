package com.ycy.controller;

import com.ycy.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("user")
public class UserController {
    
    @Resource
    UserRepository userRepository;
    
    @RequestMapping("list")
    public String list(ModelMap map) {
        map.put("allUsers", userRepository.findAll());
        return "user/list";
    }
    
}