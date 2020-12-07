package com.lagou.service.impl;

import com.lagou.pojo.Code;
import com.lagou.service.CodeInfoService;
import com.lagou.service.CodeService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CodeServiceImpl implements CodeService {
    @Autowired
    private CodeInfoService codeInfoService;
    @Override
    public boolean validate(String email, String code) {
        Code lateredCode = codeInfoService.findLateredCode(email);
        if(code.equals(lateredCode.getCode())){
            return true;
        }
        return false;
    }

}
