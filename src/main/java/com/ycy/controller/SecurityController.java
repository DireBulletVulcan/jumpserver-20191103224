package com.ycy.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import cn.dev33.satoken.stp.StpUtil;
import com.ycy.model.Operation;
import com.ycy.model.User;
import com.ycy.repository.OperationRepository;
import com.ycy.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
@RequestMapping("auth")
public class SecurityController {
    //声明一个Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityController.class);
    
    @Resource
    UserRepository userRepository;
    
    @Resource
    OperationRepository operationRepository;
    
    @GetMapping("login")
    public String loginPage() {
        return "login";
    }
    
    @PostMapping("login")
    public String login(@RequestParam("name") String name, @RequestParam("password") String password) {
        // 处理用户登录请求
        User user = userRepository.findByName(name);
        if (user == null) {
            return "redirect:/auth/login";
        }
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
        if (!result.verified) {
            return "redirect:/auth/login";
        }
        // 用户输入的用户名与密码匹配，登录成功
        StpUtil.login(user.getId());
        
        return "redirect:/";
    }
    
    @GetMapping("changePassword")
    public String changePasswordPage() {
        return "changePassword";
    }
    
    @PostMapping("changePassword")
    public String changePassword(@RequestParam("currentPassword") String currentPassword,
                                 @RequestParam("newPassword1") String newPassword1,
                                 @RequestParam("newPassword2") String newPassword2) {
        Long userId = StpUtil.getLoginIdAsLong();
        User user = userRepository.findById(userId).get();
        BCrypt.Result result = BCrypt.verifyer().verify(currentPassword.toCharArray(), user.getPassword());
        if (!result.verified) {
            StpUtil.logout();
            return "redirect:/auth/login";
        }
        
        if (!newPassword1.equals(newPassword2)) {
            return "redirect:/auth/changePassword";
        }
        
        String hashedPassword = BCrypt.withDefaults().hashToString(12, newPassword1.toCharArray());
        user.setPassword(hashedPassword);
        this.userRepository.save(user);
        LOGGER.info("修改本人密码成功！");
        operationRepository.save(new Operation("security", "修改本人密码: " + user.getId()));
        StpUtil.logout();
        return "redirect:/auth/login";
    }
    
    @GetMapping("logout")
    public String logout() {
        StpUtil.logout();
        return "redirect:/auth/login";
    }
    
}