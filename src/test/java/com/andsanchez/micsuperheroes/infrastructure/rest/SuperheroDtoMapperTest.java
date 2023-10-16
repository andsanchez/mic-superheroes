package com.andsanchez.micsuperheroes.infrastructure.rest;

import com.andsanchez.micsuperheroes.domain.Superhero;
import com.andsanchez.micsuperheroes.mother.SuperheroMother;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class SuperheroDtoMapperTest {

    private static final String SOME_NAME = "someName";

    private final SuperheroDtoMapper mapper = new SuperheroDtoMapper();

    @Test
    void dtoToSuperhero() {
        SuperheroDto superheroDto = new SuperheroDto()
                .id(1L)
                .name(SOME_NAME);

        Superhero superhero = mapper.dtoToSuperhero(superheroDto);

        Assertions.assertThat(superhero).isNotNull()
                .hasNoNullFieldsOrProperties()
                .returns(superheroDto.getId(), Superhero::id)
                .returns(superheroDto.getName(), Superhero::name);
    }

    @Test
    void superheroToDto() {
        Superhero superhero = SuperheroMother.create();

        SuperheroDto superheroDto = mapper.superheroToDto(superhero);

        Assertions.assertThat(superheroDto).isNotNull()
                .hasNoNullFieldsOrProperties()
                .returns(superhero.id(), SuperheroDto::getId)
                .returns(superhero.name(), SuperheroDto::getName);
    }

    @Test
    void createSuperheroRequestDtoToSuperhero() {
        SuperheroRequestDto superheroRequestDto = new SuperheroRequestDto(SOME_NAME);

        Superhero superhero = mapper.createSuperheroRequestDtoToSuperhero(superheroRequestDto);

        Assertions.assertThat(superhero).isNotNull()
                .returns(null, Superhero::id)
                .returns(superheroRequestDto.getName(), Superhero::name);
    }

    @Test
    void updateSuperheroRequestDtoToSuperhero() {
        Long superheroId = 1L;
        SuperheroRequestDto superheroRequestDto = new SuperheroRequestDto(SOME_NAME);

        Superhero superhero = mapper.updateSuperheroRequestDtoToSuperhero(superheroId, superheroRequestDto);

        Assertions.assertThat(superhero).isNotNull()
                .returns(superheroId, Superhero::id)
                .returns(superheroRequestDto.getName(), Superhero::name);
    }
}