package com.andsanchez.micsuperheros.infrastructure.persistence;

import com.andsanchez.micsuperheros.domain.Superhero;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SuperheroEntityMapper{

    private final ModelMapper mapper;

    Superhero entityToSuperHero(SuperheroEntity superheroEntity) {
        return mapper.map(superheroEntity, Superhero.class);
    }

    SuperheroEntity superheroToEntity(Superhero superhero) {
        return mapper.map(superhero, SuperheroEntity.class);
    }

}
