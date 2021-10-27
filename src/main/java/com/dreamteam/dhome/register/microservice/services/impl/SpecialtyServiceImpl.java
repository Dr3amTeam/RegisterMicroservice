package com.dreamteam.dhome.register.microservice.services.impl;


import com.dreamteam.dhome.register.microservice.entities.Specialty;
import com.dreamteam.dhome.register.microservice.repositories.ISpecialtyRepository;
import com.dreamteam.dhome.register.microservice.services.ISpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SpecialtyServiceImpl implements ISpecialtyService {

    @Autowired
    private ISpecialtyRepository specialtyRepository;

    @Override
    @Transactional
    public Specialty save(Specialty specialty) throws Exception {
        return specialtyRepository.save(specialty);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        specialtyRepository.deleteById(id);
    }

    @Override
    public List<Specialty> getAll() throws Exception {
        return specialtyRepository.findAll();
    }

    @Override
    public Optional<Specialty> getById(Long id) throws Exception {
        return specialtyRepository.findById(id);
    }

    @Override
    public Specialty findByName(String name) throws Exception {
        return specialtyRepository.findByName(name);
    }
}
