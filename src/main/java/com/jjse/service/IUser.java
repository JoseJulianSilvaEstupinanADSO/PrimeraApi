package com.jjse.service;

import com.jjse.model.entity.User;

import ch.qos.logback.core.net.server.Client;

public interface IUser {

    User save(User user);

    User findById(Integer id);

    void delete(User user);


}
