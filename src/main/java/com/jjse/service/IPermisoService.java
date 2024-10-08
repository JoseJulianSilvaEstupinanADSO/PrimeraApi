package com.jjse.service;


import java.util.List;

import com.jjse.model.dto.PermisoDto;
import com.jjse.model.entity.Permiso;

public interface IPermisoService {

    List<Permiso> listAll();

    Permiso save(PermisoDto permiso);

    Permiso findById(Integer id);
    
    void delete(Permiso permiso);

    boolean existsById(Integer id);
}
