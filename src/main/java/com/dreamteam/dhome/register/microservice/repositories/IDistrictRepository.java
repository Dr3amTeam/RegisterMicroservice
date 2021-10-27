package com.dreamteam.dhome.register.microservice.repositories;

import com.dreamteam.dhome.register.microservice.entities.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDistrictRepository extends JpaRepository<District, Long> {
    public District findByName(String name);
    public List<District> findByCity_Id(Long id);
}
