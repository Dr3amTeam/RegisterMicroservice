package com.dreamteam.dhome.register.microservice.services;
import com.dreamteam.dhome.register.microservice.entities.City;

public interface ICityService extends CrudService<City>{
    public City findByName(String name) throws Exception;
}
