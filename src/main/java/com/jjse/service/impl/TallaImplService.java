package com.jjse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jjse.model.dao.TallaDao;
import com.jjse.model.dto.TallaDto;
import com.jjse.model.entity.Talla;
import com.jjse.model.entity.User;
import com.jjse.service.ITallaService;


@Service
public class TallaImplService implements ITallaService {
    
    @Autowired
    private TallaDao tallaDao;

    @Override
    public List<Talla> listAll(){
        return (List) tallaDao.findAll();    
    }

    @Transactional
    @Override
    public Talla save(TallaDto tallaDto){
        Talla talla = Talla.builder()
                            .id(tallaDto.getId())
                            .tipo(tallaDto.getTipo())
                            .build();
        return tallaDao.save(talla);
            
    }
    @Transactional
    @Override
    public void delete(Talla talla){
        tallaDao.delete(talla);
    }

    @Override
    public boolean existsById(Integer id){
        return tallaDao.existsById(id);
    }
    @Transactional(readOnly = true)
    @Override
    public Talla findById(Integer id){
        return tallaDao.findById(id).orElse(null);
    }
}

