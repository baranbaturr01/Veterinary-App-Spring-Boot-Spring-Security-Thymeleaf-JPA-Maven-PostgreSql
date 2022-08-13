package com.baranbatur.vetApp.repository;

import com.baranbatur.vetApp.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAnimalRepository extends JpaRepository<Animal, Long> {

    @Query(value = "SELECT a FROM Animal a WHERE a.name like %?1%  OR  a.animalOwner.name LIKE %?1%")
    List<Animal> search(String keyword);

    Animal findByName(String name);

    List<Animal> findByAnimalOwnerId(Long id);
}
