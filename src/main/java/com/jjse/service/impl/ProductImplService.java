package com.jjse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jjse.model.dao.ProductDao;
import com.jjse.model.dto.ProductDto;
import com.jjse.model.entity.Product;
import com.jjse.service.IProductService;


@Service
public class ProductImplService implements IProductService{

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> listALL(){
        return (List) productDao.findAll();
    }

    @Transactional
    @Override
    public Product save(ProductDto productDto){
        Product product = Product.builder()
                           .id(productDto.getId())
                           .name(productDto.getName())
                           .precio(productDto.getPrecio())
                           .state(productDto.getState())
                           .stock(productDto.getStock())
                           .talla(productDto.getTalla())
                           .build();
        return productDao.save(product);
    }

    @Transactional(readOnly = true)
    @Override
    public Product findById(Integer id){
        return productDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Product product){
        productDao.delete(product);
    }

    @Override
    public boolean existsById(Integer id){
        return productDao.existsById(id);
    }

}
