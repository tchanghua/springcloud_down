package com.lagou.service;

import com.lagou.pojo.User;

public interface UserService {
    public User findUserByEmail(String email);

    public User findUserByEmaiAndPwd(String email,String password);

    public User findUserByToken(String token);

    public void saveUser(String email,String password,String token);

}
