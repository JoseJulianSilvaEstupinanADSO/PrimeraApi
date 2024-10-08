package com.jjse.model.dto;

import java.io.Serializable;
import java.util.Set;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder

public class RolDto implements Serializable {

    private Integer id;

    private String name;

    private Set<PermisoDto> permisos;
}
