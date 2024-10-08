package com.jjse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jjse.model.dto.TallaDto;
import com.jjse.model.entity.Talla;
import com.jjse.model.pyload.MessageResponse;
import com.jjse.service.ITallaService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/tallas")
public class TallaController {

    @Autowired
    private ITallaService tallaService;

    @GetMapping
    public ResponseEntity<?> showAll() {
        List<Talla> getList = tallaService.listAll();

        if (getList.size() <= 0) {
            return new ResponseEntity<>(
                            MessageResponse.builder()
                                        .message("No hay Productos registrados")
                                        .object(null)
                                        .build(),
                                        HttpStatus.OK);  
        }

        return new ResponseEntity<>(
            MessageResponse.builder()
                            .message("Registros encontrados")
                            .object(getList)
                            .build(),
                            HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<?> registerTalla(@RequestBody TallaDto talla) {
        
        Talla tallaSave = null;
        
        try {
            
            tallaSave = tallaService.save(talla);
            return new ResponseEntity<>(
                            MessageResponse.builder()
                            .message("Talla guardado")
                            .object(TallaDto.builder()
                                .tipo(tallaSave.getTipo())
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
    public ResponseEntity<?> updateTalla(@PathVariable Integer id, @RequestBody TallaDto tallaDto) {
        
        Talla tallaUpdate = null;

        try {
            
            if (tallaService.existsById(id)) {
                tallaDto.setId(id);
                tallaUpdate = tallaService.save(tallaDto);
                return new ResponseEntity<>(
                                MessageResponse.builder()
                                    .message("Talla actualizada")
                                    .object(TallaDto.builder()
                                        .tipo(tallaUpdate.getTipo())
                                        .build())
                                    .build(),
                            HttpStatus.CREATED);
            }
            else{
                return new ResponseEntity<>(
                            MessageResponse.builder()
                                        .message("La talla no fue encontrando")
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
    public ResponseEntity<?> deleteTalla(@PathVariable Integer id){
        try {
            
            Talla tallaDelete = tallaService.findById(id);
            tallaService.delete(tallaDelete);
            return new ResponseEntity<>(tallaDelete,HttpStatus.NO_CONTENT);
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
