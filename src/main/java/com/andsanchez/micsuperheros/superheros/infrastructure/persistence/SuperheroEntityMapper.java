package com.andsanchez.micsuperheros.superheros.infrastructure.persistence;

import com.andsanchez.micsuperheros.superheros.domain.Superhero;
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
