package com.dreamteam.dhome.register.microservice.repositories;

import com.dreamteam.dhome.register.microservice.entities.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISpecialtyRepository extends JpaRepository<Specialty, Long> {
    public Specialty findByName (String name);
}
