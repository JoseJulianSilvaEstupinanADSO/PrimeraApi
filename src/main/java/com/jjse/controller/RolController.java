package com.jjse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jjse.model.dao.RolDao;
import com.jjse.model.dto.ProductDto;
import com.jjse.model.dto.RolDto;
import com.jjse.model.entity.Rol;
import com.jjse.model.pyload.MessageResponse;
import com.jjse.service.IRolService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/roles")
public class RolController {

    @Autowired
    private IRolService rolService;

    @GetMapping
    public ResponseEntity<?> showAll() {
        List<Rol> getList = rolService.listALL();

        if (getList.size() <=0) {
            return new ResponseEntity<>(
                            MessageResponse.builder()
                                        .message("No hay Roles registrados")
                                        .object(null)
                                        .build(),
                                        HttpStatus.OK);    
        }
        return new ResponseEntity<>(
            MessageResponse.builder()
                            .message("Roles encontrados")
                            .object(getList)
                            .build(),
                            HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> registerRol(@RequestBody RolDto rolDto) {
        
        Rol rolSave = null;

        try {
            
            rolSave = rolService.save(rolDto);
            return new ResponseEntity<>(
                            MessageResponse.builder()
                                .message("Rol registrado correctamente")
                                .object(RolDto.builder()
                                    .name(rolSave.getName())
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
    public ResponseEntity putMethodName(@PathVariable Integer id, @RequestBody RolDto rolDto) {
        
        Rol rolUpdate = null;

        try {
            
            if (rolService.existsById(id)) {
                rolDto.setId(id);
                rolUpdate = rolService.save(rolDto);
                return new ResponseEntity<>(
                                MessageResponse.builder()
                                    .message("Rol modificado")
                                    .object(RolDto.builder()
                                        .id(rolUpdate.getId())
                                        .name( rolUpdate.getName())
                                        .build())
                                    .build(),
                                HttpStatus.CREATED);
            }
            else{
                return new ResponseEntity<>(
                            MessageResponse.builder()
                                        .message("El Producto no fue encontrando")
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
    public ResponseEntity<?> deleteRol(@PathVariable Integer id){

        try {
            
            Rol rolDelete = rolService.findById(id);
            rolService.delete(rolDelete);

            return new ResponseEntity<>(rolDelete, HttpStatus.NO_CONTENT);
        
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                            MessageResponse.builder()
                                        .message(exDt.getMessage())
                                        .object(null)
                                        .build()
                            ,HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> showByid(@PathVariable Integer id) {
        
        Rol rol = rolService.findById(id);

        if (rol == null) {
            return new ResponseEntity<>(
                MessageResponse.builder()
                            .message("El registro que intenta buscar no existe")
                            .object(null)
                            .build()
                ,HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(
            MessageResponse.builder()
                        .message("Rol encontrado")
                        .object(RolDto.builder()
                            .name(rol.getName())
                            .build())
                        .build(),
            HttpStatus.OK);
    }
    
    
}
