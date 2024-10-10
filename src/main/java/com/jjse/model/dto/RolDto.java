package com.jjse.model.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder

public class RolDto implements Serializable {

    private Integer id;

    private String name;

    private List<PermisoDto> permisos;
}
