package com.andsanchez.micsuperheros.infrastructure.persistence;

import com.andsanchez.micsuperheros.domain.Superhero;
import org.springframework.stereotype.Component;

@Component
public class SuperheroEntityMapper {

    Superhero entityToSuperHero(SuperheroEntity superheroEntity) {
        return new Superhero(superheroEntity.getId(), superheroEntity.getName());
    }

    SuperheroEntity superheroToEntity(Superhero superhero) {
        SuperheroEntity superheroEntity = new SuperheroEntity();
        superheroEntity.setId(superhero.id());
        superheroEntity.setName(superhero.name());
        return superheroEntity;
    }

}
