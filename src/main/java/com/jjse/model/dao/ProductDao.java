package com.jjse.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.jjse.model.entity.Product;

public interface ProductDao extends CrudRepository<Product, Integer> {

}
