package com.andsanchez.micsuperheroes.domain;

import java.util.List;

public interface SuperheroRepository {

  Superhero save(Superhero superhero);

  Superhero findById(Long id);

  List<Superhero> findAll();

  List<Superhero> findByNameContainingIgnoreCase(String name);

  void delete(Superhero superhero);

}
