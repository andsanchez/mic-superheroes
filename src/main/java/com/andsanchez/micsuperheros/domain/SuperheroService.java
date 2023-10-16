package com.andsanchez.micsuperheros.domain;

import java.util.List;

public interface SuperheroService {

    Superhero createSuperhero(Superhero superhero);

    Superhero getSuperheroById(Long id);

    List<Superhero> getAllSuperheroes();

    List<Superhero> findSuperheroesByNameContainingIgnoreCase(String name);

    Superhero updateSuperhero(Superhero superhero);

    boolean deleteSuperhero(Long superheroId);

}
