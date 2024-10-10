package com.jjse.service.impl;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jjse.model.dao.RolDao;
import com.jjse.model.dao.UserDao;
import com.jjse.model.dto.UserDto;
import com.jjse.model.entity.Rol;
import com.jjse.model.entity.User;
import com.jjse.service.IUserService;



@Service
public class UserImplService implements IUserService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private RolDao rolDao;


    @Override
    public List<User> listAll(){
        return (List) userDao.findAll();
    }


    @Transactional
    @Override
    public User save(UserDto userDto) {
    
        
        User user = User.builder()
            .id(userDto.getId())
            .name(userDto.getName())
            .email(userDto.getEmail())
            .user(userDto.getUser())
            .password(userDto.getPassword())
            .documento(userDto.getDocumento())
            .telefono(userDto.getTelefono())
            .edad(userDto.getEdad())
            .build();

            List<Rol> roles = userDto.getRoles().stream()
                                .map(rolId -> rolDao.findById(rolId)
                                                        .orElseThrow(() -> new RuntimeException("Role not found")))
                                .collect(Collectors.toList());

        user.setRoles(roles);
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
        
        user.getRoles().clear();

        userDao.save(user);

        userDao.delete(user);
    }


    @Override
    public boolean existsById(Integer id){
        return userDao.existsById(id);
    }

}
