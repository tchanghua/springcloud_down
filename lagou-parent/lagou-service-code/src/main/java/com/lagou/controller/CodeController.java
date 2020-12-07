package com.lagou.controller;

import com.lagou.pojo.Code;
import com.lagou.service.CodeInfoService;
import com.lagou.service.EmailService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/code")
public class CodeController {
    @Reference
    private EmailService emailService;
    @Autowired
    private CodeInfoService codeInfoService;

    @GetMapping("/create/{email}")
    public boolean create(@PathVariable String email){
        String code =gerateCode();//验证码
        //保存到数据库
        codeInfoService.saveCode(email,code);

        //调用微服务  lagou-service-code
        boolean b = emailService.sendEmail(email, code);

        return b;
    }

    @GetMapping("/validate/{email}/{code}")
    public boolean validate(@PathVariable String email,@PathVariable String code){

        Code lateredCode = codeInfoService.findLateredCode(email);
        if(code.equals(lateredCode.getCode())){
            return true;
        }
        return false;
    }

    public String gerateCode(){
        String code = "";
        Random random = new Random();
        for (int i = 0; i < 6; i++) {

            int r = random.nextInt(10); //每次随机出一个数字（0-9）

            code = code + r;  //把每次随机出的数字拼在一起

        }
        System.out.println(code);
        return code;
    }
}
