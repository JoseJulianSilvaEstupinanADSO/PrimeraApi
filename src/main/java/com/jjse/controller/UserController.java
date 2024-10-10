package com.jjse.controller;

import org.springframework.web.bind.annotation.RestController;

import com.jjse.model.dto.UserDto;
import com.jjse.model.entity.User;
import com.jjse.model.pyload.MessageResponse;
import com.jjse.service.IUserService;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;


@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private IUserService userService;
    

    @GetMapping()
    public ResponseEntity<?> showAll(){

        List<User> getList = userService.listAll();

        if (getList.size() <= 0 ){
            return new ResponseEntity<>(
                            MessageResponse.builder()
                                        .message("No hay registros")
                                        .object(null)
                                        .build()
                            ,HttpStatus.OK);
        }

        return new ResponseEntity<>(
            MessageResponse.builder()
                        .message(null)
                        .object(getList)
                        .build()
            ,HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        User userSave = null;
        try {
            userSave = userService.save(userDto);
            // Respuesta exitosa con el código HTTP 201 (CREATED)
            return new ResponseEntity<>(
                    MessageResponse.builder()
                            .message("User created successfully")
                            .object(UserDto.builder()
                                    .id(userSave.getId())
                                    .name(userSave.getName())
                                    .email(userSave.getEmail())
                                    .user(userSave.getUser())
                                    .password(userSave.getPassword())
                                    .documento(userSave.getDocumento())
                                    .telefono(userSave.getTelefono())
                                    .edad(userSave.getEdad())
                                    .build())
                            .build(),
                    HttpStatus.CREATED);
        } catch (DataAccessException exDt) {
            // Manejo de error, mejor usar 500 o 400 en vez de 405 (METHOD_NOT_ALLOWED)
            return new ResponseEntity<>(
                    MessageResponse.builder()
                            .message("Error saving user: " + exDt.getMessage())
                            .object(null)
                            .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR); // O BAD_REQUEST dependiendo del error
        } catch (Exception ex) {
            // Captura general de excepciones para manejar errores no anticipados
            return new ResponseEntity<>(
                    MessageResponse.builder()
                            .message("Unexpected error: " + ex.getMessage())
                            .object(null)
                            .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UserDto userDto, @PathVariable Integer id){
        User userUpdate = null;
        try {

            if (userService.existsById(id)) {
                userDto.setId(id);
                userUpdate = userService.save(userDto);
                return new ResponseEntity<>(
                                MessageResponse.builder()
                                    .message("User Save")
                                    .object(UserDto.builder()
                                        .id(userUpdate.getId())
                                        .name(userUpdate.getName())
                                        .email(userUpdate.getEmail())
                                        .user(userUpdate.getUser())
                                        .password(userUpdate.getPassword())
                                        .documento(userUpdate.getDocumento())
                                        .telefono(userUpdate.getTelefono())
                                        .edad(userUpdate.getEdad())
                                        .build())
                                    .build()
                                ,HttpStatus.CREATED); 
                
            }
            else{
                return new ResponseEntity<>(
                            MessageResponse.builder()
                                        .message("El usuario no fue encontrando")
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
    public ResponseEntity<?> deleteeUser(@PathVariable Integer id){
        try {

            User userDelete = userService.findById(id);
            userService.delete(userDelete);

            return new ResponseEntity<>(userDelete, HttpStatus.NO_CONTENT);
            
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
    public ResponseEntity<?> showByid(@PathVariable Integer id){
        User user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(
                            MessageResponse.builder()
                                        .message("El registro que intenta buscar no existe")
                                        .object(null)
                                        .build()
                            ,HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(
            MessageResponse.builder()
                        .message(null)
                        .object(UserDto.builder()
                            .id(user.getId())
                            .name(user.getName())
                            .email(user.getEmail())
                            .user(user.getUser())
                            .password(user.getPassword())
                            .documento(user.getDocumento())
                            .telefono(user.getTelefono())
                            .edad(user.getEdad())
                            .build())
                        .build()
            ,HttpStatus.OK);
    }

}
