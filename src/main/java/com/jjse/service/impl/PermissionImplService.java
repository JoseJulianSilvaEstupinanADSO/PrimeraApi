package com.jjse.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jjse.model.dao.PermisoDao;
import com.jjse.model.dto.PermisoDto;
import com.jjse.model.entity.Permiso;
import com.jjse.service.IPermisoService;

@Service
public class PermissionImplService implements IPermisoService {

    @Autowired
    private PermisoDao permisoDao;

    @Override
    public List<Permiso> listAll(){
        return (List) permisoDao.findAll();
    }

    @Transactional
    @Override
    public Permiso save(PermisoDto permisoDto){
        Permiso permiso = Permiso.builder()
                                    .id(permisoDto.getId())
                                    .name(permisoDto.getName())
                                    .description(permisoDto.getDescription())
                                    .build();
        return permisoDao.save(permiso);

    }

    @Transactional(readOnly = true)
    @Override
    public Permiso findById(Integer id){
        return permisoDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Permiso permiso){
        permisoDao.delete(permiso);
    }

    @Override
    public boolean existsById(Integer id){
        return permisoDao.existsById(id);
    }
     

}
