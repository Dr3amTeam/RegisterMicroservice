package com.dreamteam.dhome.register.microservice.services;


import com.dreamteam.dhome.register.microservice.entities.Specialty;

public interface ISpecialtyService extends CrudService<Specialty>{
    public Specialty findByName(String name) throws Exception;
}
