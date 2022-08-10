package com.baranbatur.vetApp.repository;

import com.baranbatur.vetApp.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAnimalRepository extends JpaRepository<Animal, Long> {

}
