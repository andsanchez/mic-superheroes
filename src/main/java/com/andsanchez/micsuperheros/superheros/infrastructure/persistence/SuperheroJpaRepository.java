package com.andsanchez.micsuperheros.superheros.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuperheroJpaRepository extends JpaRepository<SuperheroEntity, Long> {

    List<SuperheroEntity> findByNameContainingIgnoreCase(String name);

}
