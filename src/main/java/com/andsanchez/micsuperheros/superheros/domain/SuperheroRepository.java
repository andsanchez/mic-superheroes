package com.andsanchez.micsuperheros.superheros.domain;

import java.util.List;

public interface SuperheroRepository {

  Superhero save(Superhero superhero);

  Superhero findById(Long id);

  List<Superhero> findAll();

  List<Superhero> findByNameContainingIgnoreCase(String name);

  void delete(Superhero superhero);

}
