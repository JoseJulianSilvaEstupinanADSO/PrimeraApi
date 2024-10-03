package com.jjse.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jjse.model.dao.UserDao;
import com.jjse.model.entity.User;
import com.jjse.service.IUser;



@Service
public class UserImpl implements IUser{

    @Autowired
    private UserDao userDao;

    @Transactional()
    @Override
    public User save(User user) {
       return userDao.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User findById(Integer id) {
        return userDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(User user) {
        userDao.delete(user);
    }
}
