package com.jjse.model.dto;


import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
public class UserDto implements Serializable {

    private Integer id;

    private String name;

    private String last_name;

    private String email;

    private Date date_register;
    
    
}
