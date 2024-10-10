package com.jjse.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.jjse.model.entity.Permiso;

public interface PermisoDao extends CrudRepository<Permiso, Integer> {
    Permiso findByName(String name);
}
