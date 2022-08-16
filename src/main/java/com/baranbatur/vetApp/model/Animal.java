package com.baranbatur.vetApp.model;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Entity
@Table(name = "animal")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_owner_id")
    @Fetch(FetchMode.JOIN)
    private AnimalOwner animalOwner;

    @Column(name = "type")
    private String type;

    @Column(name = "genus")
    private String genus;

    @Column(name = "name")
    private String name;

    //must be number
    @Column(name = "age")
    private Integer age;

    @Column(name = "description")
    private String description;
}
