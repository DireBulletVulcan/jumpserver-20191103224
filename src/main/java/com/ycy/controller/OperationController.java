package com.ycy.controller;

import com.ycy.repository.OperationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("operation")
public class OperationController {
    
    @Resource
    OperationRepository operationRepository;
    
    @RequestMapping("list")
    public String list(ModelMap map) {
        map.put("allOperations", operationRepository.findAll());
        return "operation/list";
    }
    
}