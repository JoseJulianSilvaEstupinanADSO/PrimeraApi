package com.jjse.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.jjse.model.dto.PermisoDto;
import com.jjse.model.entity.Permiso;
import com.jjse.model.pyload.MessageResponse;
import com.jjse.service.IPermisoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/permisos")
public class PermisoController {

    @Autowired
    private IPermisoService permisoService;

    @GetMapping
    public ResponseEntity<?> showAll() {
        List<Permiso> getList = permisoService.listAll();

        if (getList.size() <= 0) {
            return new ResponseEntity<>(
                            MessageResponse.builder()
                                        .message("No hay Permisos registrados")
                                        .object(null)
                                        .build(),
                                        HttpStatus.OK);  
        }
        return new ResponseEntity<>(
            MessageResponse.builder()
                            .message("Permisos encontrados")
                            .object(getList)
                            .build(),
                            HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<?> registerPermiso(@RequestBody PermisoDto permiso) {

        Permiso permisosave = null;

        try {
            
            permisosave = permisoService.save(permiso);
            return new ResponseEntity<>(
                            MessageResponse.builder()
                            .message("Permiso creado satisfactoriamente")
                            .object(PermisoDto.builder()
                                .name(permisosave.getName())
                                .description(permisosave.getDescription())
                                .build())
                            .build(),
                            HttpStatus.CREATED);

        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                            MessageResponse.builder()
                                        .message(exDt.getMessage())
                                        .object(null)
                                        .build()
                            ,HttpStatus.METHOD_NOT_ALLOWED);
        }  
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePermiso(@PathVariable Integer id, @RequestBody PermisoDto permisoDto) {

        Permiso permisoUpdate = null;

        try {
            
            if (permisoService.existsById(id)) {
                permisoDto.setId(id);
                permisoUpdate = permisoService.save(permisoDto);
                return new ResponseEntity<>(
                                MessageResponse.builder()
                                    .message("Usuario actualizado")
                                    .object(PermisoDto.builder()
                                        .name(permisoUpdate.getName())
                                        .description(permisoUpdate.getDescription())
                                        .build())
                                    .build(),
                                HttpStatus.CREATED);   

            }
            else{
                return new ResponseEntity<>(
                            MessageResponse.builder()
                                        .message("El permiso no fue encontrando")
                                        .object(null)
                                        .build()
                            ,HttpStatus.NOT_FOUND);    
            }

        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                            MessageResponse.builder()
                                        .message(exDt.getMessage())
                                        .object(null)
                                        .build()
                            ,HttpStatus.METHOD_NOT_ALLOWED);
        }

    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePermiso(@PathVariable Integer id){
        try {
            
            Permiso permisoDelete = permisoService.findById(id);
            permisoService.delete(permisoDelete);
            return new ResponseEntity<>(permisoDelete,HttpStatus.NO_CONTENT);

        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                            MessageResponse.builder()
                                        .message(exDt.getMessage())
                                        .object(null)
                                        .build()
                            ,HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
}
