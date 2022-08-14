package com.baranbatur.vetApp.repository;

import com.baranbatur.vetApp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {

    @Query("select r from Role r where r.name=?1")
    public Role findByName(String name);

}
