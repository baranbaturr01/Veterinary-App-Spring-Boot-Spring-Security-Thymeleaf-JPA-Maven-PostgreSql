package com.baranbatur.vetApp.repository;

import com.baranbatur.vetApp.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAnimalRepository extends JpaRepository<Animal, Long> {


    @Query("SELECT a FROM Animal a WHERE lower( a.name) LIKE lower( CONCAT('%',:keyword,'%'))or lower( a.animalOwner.name) LIKE lower( CONCAT('%',:keyword,'%'))")
    List<Animal> search(String keyword);

    List<Animal> findByAnimalOwnerId(Long id);
}
