package com.lagou.controller;

import com.lagou.pojo.User;
import com.lagou.service.CodeService;
import com.lagou.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@RefreshScope
public class UserController {
    @Autowired
    private UserService userService;
    @Reference
    private CodeService codeService;


    @GetMapping("/register/{email}/{password}/{code}")
    public boolean register(@PathVariable String email, @PathVariable String password, @PathVariable String code, HttpServletResponse response){
        //判断验证码的是否有效
        boolean validate = codeService.validate(email, code);
        if (!validate){
            return false;
        }

        String token_uuid = UUID.randomUUID().toString();
        //保存email password taken
        userService.saveUser(email,password,token_uuid);

        Cookie cookie = new Cookie("token",token_uuid);
        response.addCookie(cookie);
        return true;
    }

    @GetMapping("/isRegistered/{email}")
    public boolean isRegistered(@PathVariable String email){
        User userByEmail = userService.findUserByEmail(email);
        if (userByEmail ==null){
            return false;
        }
        return true;
    }

    @GetMapping("/login/{email}/{password}")
    public String login(@PathVariable String email, @PathVariable String password, HttpServletResponse response) throws IOException {
        System.out.println(email+"=====>>>"+password);
        User user1 = userService.findUserByEmaiAndPwd(email,password);
        if (user1 ==null){
            return "";
        }else{
            //登录成功 跳转
            response.sendRedirect("http://localhost/api/user/info/"+user1.getToken());
        }

        return "";
    }

    @GetMapping("/info/{token}")
    public String info(@PathVariable String token, HttpServletResponse response) throws IOException {
        //通过token 查询email
        User user1 = userService.findUserByToken(token);
        System.out.println(token);

        return user1.getEmail();
    }
}
