package com.zking.shiro.controller;

import com.zking.shiro.model.User;
import com.zking.shiro.service.IUserService;
import com.zking.shiro.utils.PasswordHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class TestController {

    @Resource
    private IUserService userService;

    @ModelAttribute
    public void init(Model model) {
        System.out.println("init");
        User user = new User();
        model.addAttribute("user", user);
    }

    @RequestMapping("/index.shtml")
    public String toLogin(Model model) {
        System.out.println("toLogin");
        return "login";
    }

    @RequestMapping("/toReg")
    public String toReg(){

        return "reg";
    }



    @RequestMapping("/login")
    public String login(Model model, User user) {
        System.out.println(user);
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        Subject subject = SecurityUtils.getSubject();
        String message = null;
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {// 捕获未知用户名异常
            message = "帐号错误";
        } catch (LockedAccountException e) {// 捕获错误登录过多的异常
            message = "帐号已锁定，请与管理员联系";
        } catch (IncorrectCredentialsException e) {// 捕获密码错误异常
            message = "密码错误";
        } catch (ExcessiveAttemptsException e) {// 捕获错误登录过多的异常
            message = "多次登录尝试失败，请60秒后再试";
        }

        if (null == message) {
            Session session = subject.getSession();//此session为org.apache.shiro.session.Session
            session.setAttribute("user", user);//登陆成功后要保存shiro的会话中，已备之后使用
            return "index";
        } else {
            model.addAttribute("message", message);
            return "login";
        }
    }

    @RequestMapping("/reg")
    public String reg(Model model,User user){
        String message=null;
        String username = user.getUsername();
        String password = user.getPassword();
        User u = userService.login(username);
        if(null!=u){
            message="用户名已存在，请重新注册!";
            model.addAttribute("message", message);
            return "reg";
        }
        user.setUsername(username);
        String salt = PasswordHelper.createSalt();
        String pwd = PasswordHelper.createCredentials(password, salt);
        user.setPassword(pwd);
        user.setSalt(salt);
        userService.insert(user);
        return "login";
    }


    @RequestMapping("/unauthorized")
    public String unauthorized() {
        return "unauthorized";
    }

    @RequestMapping("/logout")
    public String logout() {
        return "login";
    }

}
