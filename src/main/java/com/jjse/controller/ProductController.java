package com.jjse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jjse.model.dto.ProductDto;
import com.jjse.model.entity.Product;
import com.jjse.model.pyload.MessageResponse;
import com.jjse.service.IProductService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;





@RestController
@RequestMapping("/products")
public class ProductController {


    @Autowired
    private IProductService productService;

    @GetMapping
    public ResponseEntity<?> showAll() {
        
        List<Product> geList = productService.listALL();
        if (geList.size() <= 0) {
            
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
                            .object(geList)
                            .build(),
                            HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<?> registerProduct(@RequestBody ProductDto  productDto) {
       
        Product productSave = null;

        try {
            
            productSave = productService.save(productDto);
            return new ResponseEntity<>(
                            MessageResponse.builder()
                            .message("Producto guardado")
                            .object(ProductDto.builder()
                                .name(productSave.getName())
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
    public ResponseEntity<?> updateProduct(@PathVariable Integer id, @RequestBody ProductDto productDto) {
        
        Product productUpdate = null;
        try {

            if (productService.existsById(id)) {
                productDto.setId(id);
                productUpdate = productService.save(productDto);
                return new ResponseEntity<>(
                                MessageResponse.builder()
                                    .message("Usuario Actualizado")
                                    .object(ProductDto.builder()
                                        .id(productUpdate.getId())
                                        .name(productUpdate.getName())
                                        .precio(productUpdate.getPrecio())
                                        .state(productUpdate.getState())
                                        .build())
                                    .build()
                                ,HttpStatus.CREATED);   
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
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id){
        try {
            
            Product productDelete = productService.findById(id);
            productService.delete(productDelete);

            return new ResponseEntity<>(productDelete, HttpStatus.NO_CONTENT);

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
