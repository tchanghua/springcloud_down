package com.lagou.service;

import com.lagou.dao.CodeDao;
import com.lagou.pojo.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CodeInfoServiceImp implements CodeInfoService {
    @Autowired
    private CodeDao codeDao;

    @Override
    public void saveCode(String email,String code) {
        Code code1 = new Code();
        code1.setId(1L);
        code1.setCode(code);
        code1.setEmail(email);
        Date date = new Date();
        code1.setCreatetime(date);
        codeDao.save(code1);
    }

    @Override
    public Code findLateredCode(String email) {
        List<Code> codeList = codeDao.findCodeByEmail(email);
        return codeList.get(0);
    }

}
