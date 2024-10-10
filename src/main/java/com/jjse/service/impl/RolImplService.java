package com.jjse.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Attr;
import com.jjse.model.dao.PermisoDao;
import com.jjse.model.dao.RolDao;
import com.jjse.model.dto.RolDto;
import com.jjse.model.entity.Permiso;
import com.jjse.model.entity.Rol;
import com.jjse.service.IRolService;

@Service
public class RolImplService implements IRolService {

    @Autowired
    private RolDao rolDao;

    @Autowired
    private PermisoDao permisoDao;

    @Override
    public List<Rol> listALL(){
        return (List) rolDao.findAll();
    }

    @Transactional
    @Override
    public Rol save(RolDto rolDto) {
        // Obtener los permisos asociados
        List<Permiso> permisos = rolDto.getPermisos().stream()
            .map(permisoDto -> {
                // Extraer el ID del permiso del DTO
                Integer permisoId = permisoDto.getId();

                // Buscar el permiso por ID
                Optional<Permiso> permisoOpt = permisoDao.findById(permisoId);

                // Retornar el permiso encontrado
                return permisoOpt.get();
            }).toList();

        // Construir el objeto Rol usando el Builder
        Rol rol = Rol.builder()
            .id(rolDto.getId())      // Si es actualización, el ID viene en el DTO
            .name(rolDto.getName())  // Establecer el nombre del rol
            .permisos(permisos)      // Asignar los permisos
            .build();

        // Guardar el rol (ya sea nuevo o actualizado) en la base de datos
        return rolDao.save(rol);
    }

    @Transactional
    @Override
    public void delete(Rol rol){
        Optional<Rol> rolOpt = rolDao.findById(rol.getId());

        rol.getPermisos().clear();

        rolDao.save(rol);

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
