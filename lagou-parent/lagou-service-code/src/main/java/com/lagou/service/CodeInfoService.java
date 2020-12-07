package com.lagou.service;

import com.lagou.pojo.Code;

public interface CodeInfoService {
    public void saveCode(String email,String code);

    public Code findLateredCode(String email);

}
