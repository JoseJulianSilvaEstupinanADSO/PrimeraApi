package com.jjse.model.dto;


import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jjse.model.entity.Rol;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class UserDto implements Serializable {

    private Integer id;

    private String name;

    private String email;

    private String user;

    private String password;

    private String documento;

    private String telefono;

    private String edad;

    private List<Integer> roles;
    
    
}
