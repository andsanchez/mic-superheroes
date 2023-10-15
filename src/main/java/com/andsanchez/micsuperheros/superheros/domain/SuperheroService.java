package com.andsanchez.micsuperheros.superheros.domain;

import java.util.List;

public interface SuperheroService {

    Superhero createSuperhero(Superhero superhero);

    Superhero getSuperheroById(Long id);

    List<Superhero> getAllSuperheroes();

    List<Superhero> findSuperheroesByNameContaining(String name);

    Superhero updateSuperhero(Superhero superhero);

    void deleteSuperhero(Superhero superhero);

}
