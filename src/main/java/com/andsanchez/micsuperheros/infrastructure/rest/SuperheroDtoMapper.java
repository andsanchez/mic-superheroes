package com.andsanchez.micsuperheros.infrastructure.rest;

import com.andsanchez.micsuperheros.domain.Superhero;
import com.andsanchez.micsuperheros.superheros.infrastructure.rest.SuperheroDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SuperheroDtoMapper {

    private final ModelMapper mapper;

    Superhero dtoToSuperhero(SuperheroDto superheroDto) {
        return mapper.map(superheroDto, Superhero.class);
    }

    SuperheroDto superheroToDto(Superhero superhero) {
        return mapper.map(superhero, SuperheroDto.class);
    }

}
