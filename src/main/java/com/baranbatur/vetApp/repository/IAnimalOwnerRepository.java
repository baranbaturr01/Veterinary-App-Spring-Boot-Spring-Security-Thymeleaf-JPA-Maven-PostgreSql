package com.baranbatur.vetApp.repository;

import com.baranbatur.vetApp.model.AnimalOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAnimalOwnerRepository extends JpaRepository<AnimalOwner, Long> {

}
