package com.jjse.service;

import java.util.List;

import com.jjse.model.dto.TallaDto;
import com.jjse.model.entity.Talla;

public interface ITallaService {
    
    List<Talla> listAll();

    Talla save(TallaDto talla);

    Talla findById(Integer id);

    void delete(Talla talla);

    boolean existsById(Integer id);
}
