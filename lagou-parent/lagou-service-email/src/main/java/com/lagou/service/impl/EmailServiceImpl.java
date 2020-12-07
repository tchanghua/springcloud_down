package com.lagou.service.impl;

import com.lagou.service.EmailService;
import com.sun.mail.util.MailSSLSocketFactory;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.*;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender mailSender; //自动注入的Bean
    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public boolean sendValidateCodeEmail(String email, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(email);
        message.setSubject("注册验证码");
        message.setText(code);
        mailSender.send(message);
        return true;
    }

}
