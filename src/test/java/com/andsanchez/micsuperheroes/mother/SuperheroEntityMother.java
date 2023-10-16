package com.andsanchez.micsuperheroes.mother;

import com.andsanchez.micsuperheroes.infrastructure.persistence.SuperheroEntity;
import net.datafaker.Faker;

public class SuperheroEntityMother {

    public static SuperheroEntity create(){
        SuperheroEntity superheroEntity = new SuperheroEntity();
        superheroEntity.setId(1L);
        superheroEntity.setName(new Faker().superhero().name());
        return superheroEntity;
    }
}
