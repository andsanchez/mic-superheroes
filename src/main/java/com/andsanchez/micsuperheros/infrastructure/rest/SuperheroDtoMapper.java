package com.andsanchez.micsuperheros.infrastructure.rest;

import com.andsanchez.micsuperheros.domain.Superhero;
import com.andsanchez.micsuperheros.superheros.infrastructure.rest.SuperheroDto;
import com.andsanchez.micsuperheros.superheros.infrastructure.rest.SuperheroRequestDto;
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
