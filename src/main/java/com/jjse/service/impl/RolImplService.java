package com.jjse.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jjse.model.dao.RolDao;
import com.jjse.model.dto.RolDto;
import com.jjse.model.entity.Permiso;
import com.jjse.model.entity.Rol;
import com.jjse.service.IRolService;

@Service
public class RolImplService implements IRolService {

    @Autowired
    private RolDao rolDao;

    @Override
    public List<Rol> listALL(){
        return (List) rolDao.findAll();
    }

    @Transactional
    @Override
    public Rol save(RolDto rolDto){
        Rol rol = Rol.builder()
                    .id(rolDto.getId())
                    .name(rolDto.getName())
                    .build();

        if (rolDto.getPermisos() != null && !rolDto.getPermisos().isEmpty()) {
            Set<Permiso> permisos = rolDto.getPermisos().stream()
                .map(permisoDto -> Permiso.builder()
                                    .id(permisoDto.getId())
                                    .build())
                .collect(Collectors.toSet());

            rol.setPermisos(permisos);
        }
        return rolDao.save(rol);
    }

    @Transactional
    @Override
    public void delete(Rol rol){
        rolDao.delete(rol);
    }

    @Transactional
    @Override
    public boolean existsById(Integer id){
        return rolDao.existsById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Rol findById(Integer id){
        return rolDao.findById(id).orElse(null);
    }
}
