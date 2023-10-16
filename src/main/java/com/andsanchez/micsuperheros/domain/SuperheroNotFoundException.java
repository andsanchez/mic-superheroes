package com.andsanchez.micsuperheros.domain;

public class SuperheroNotFoundException extends RuntimeException {

    public SuperheroNotFoundException(Long superheroId) {
        super("Superhero with id " + superheroId + " not found");
    }
}