package com.lagou.service;

import com.lagou.dao.UserDao;
import com.lagou.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User findUserByEmail(String email) {
        User user = new User();
        user.setEmail(email);
        Example<User> example = Example.of(user);
        return userDao.findOne(example).get();
    }

    @Override
    public User findUserByEmaiAndPwd(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        Example<User> example = Example.of(user);
        return userDao.findOne(example).get();
    }

    @Override
    public User findUserByToken(String token) {
        User user = new User();
        user.setToken(token);
        Example<User> example = Example.of(user);
        Optional<User> optional = userDao.findOne(example);
        return optional.isPresent() ? optional.get(): null;
    }

    @Override
    public void saveUser(String email, String password, String token) {
        User user = new User();
        user.setId(1L);
        user.setEmail(email);
        user.setPassword(password);
        user.setToken(token);
        userDao.save(user);
    }

}
