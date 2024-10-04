package com.jjse.service;

import java.util.List;

import com.jjse.model.dto.UserDto;
import com.jjse.model.entity.User;

import ch.qos.logback.core.net.server.Client;

public interface IUserService {

    List<User> listAll();

    User save(UserDto user);

    User findById(Integer id);

    void delete(User user);

    boolean existsById(Integer id);


}
