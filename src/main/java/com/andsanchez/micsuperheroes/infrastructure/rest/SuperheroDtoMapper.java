package com.andsanchez.micsuperheroes.infrastructure.rest;

import com.andsanchez.micsuperheroes.domain.Superhero;
import org.springframework.stereotype.Component;

@Component
public class SuperheroDtoMapper {

    Superhero dtoToSuperhero(SuperheroDto superheroDto) {
        return new Superhero(superheroDto.getId(), superheroDto.getName());
    }

    SuperheroDto superheroToDto(Superhero superhero) {
        return new SuperheroDto()
                .id(superhero.id())
                .name(superhero.name());
    }

    Superhero createSuperheroRequestDtoToSuperhero(SuperheroRequestDto superheroRequestDto) {
        return new Superhero(null, superheroRequestDto.getName());
    }
    Superhero updateSuperheroRequestDtoToSuperhero(Long id, SuperheroRequestDto superheroRequestDto) {
        return new Superhero(id, superheroRequestDto.getName());
    }

}
