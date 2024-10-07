package com.jjse.service;

import java.util.List;

import com.jjse.model.dto.ProductDto;
import com.jjse.model.entity.Product;

public interface IProductService {

    List<Product> listALL();

    Product save(ProductDto product);

    Product findById(Integer id);

    void delete(Product product);

    boolean existsById(Integer id);

}
