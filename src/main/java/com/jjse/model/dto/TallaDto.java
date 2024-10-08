package com.jjse.model.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class TallaDto implements Serializable {
    
    private Integer id;

    private String tipo;

}
