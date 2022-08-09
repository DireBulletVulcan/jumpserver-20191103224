package com.ycy.controller;

import com.ycy.model.User;
import com.ycy.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    
    @GetMapping("save")
    public String saveForm(ModelMap map, @RequestParam(value = "id", required=false) Long id) {
        User user = id != null ? this.userRepository.findById(id).get() : new User();
        map.put("user", user);
        return "user/save";
    }
    
    @PostMapping("save")
    public String save(User user) {
        if (user.getId() != null) {
            // 更新的逻辑：先从数据库中查找到已保持的对象，再更加表单提交的值，去更新此对象，最后保存
            User savedUser = this.userRepository.findById(user.getId()).get();
            savedUser.setName(user.getName());
            this.userRepository.save(savedUser);
        } else {
            this.userRepository.save(user);
        }
        return "redirect:/user/list";
    }
    
    @GetMapping("del")
    public String del(Long id) {
        this.userRepository.deleteById(id);
        return "redirect:/user/list";
    }
    
}