package com.lagou.service;

public interface EmailService {

    boolean sendValidateCodeEmail(String email,String code);

}
