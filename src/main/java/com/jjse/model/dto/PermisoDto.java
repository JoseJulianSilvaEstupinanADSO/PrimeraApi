package com.jjse.model.dto;

import java.io.Serializable;


import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class PermisoDto implements Serializable {

    private Integer id;

    private String name;

    private String description;

}
