package com.jjse.service;

import java.util.List;

import com.jjse.model.dto.RolDto;
import com.jjse.model.entity.Rol;

public interface IRolService {

    List<Rol> listALL();

    Rol save(RolDto rol);

    Rol findById(Integer id);

    void delete(Rol rol);

    boolean existsById(Integer id);
}
