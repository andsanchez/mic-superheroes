package com.andsanchez.micsuperheros.infrastructure.persistence;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "superheroes")
public class SuperheroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

}
