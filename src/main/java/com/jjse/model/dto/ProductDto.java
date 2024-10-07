package com.jjse.model.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;


@Data
@ToString
@Builder
public class ProductDto  implements Serializable  {
    
    private Integer id;

    private String name;

    private String precio;

    private String state;
}
