package com.lagou.controller;

import com.lagou.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/sendEmail/{email}/{code}")
    public boolean sendEmail(@PathVariable String email,@PathVariable String code){
        try {
            return emailService.sendValidateCodeEmail(email,code);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
