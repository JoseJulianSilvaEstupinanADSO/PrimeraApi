package com.jjse.model.dto;


import java.io.Serializable;


import com.fasterxml.jackson.annotation.JsonIgnore;


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

    @JsonIgnore
    private String password;

    private String documento;

    private String telefono;

    private String edad;
    
    
}
