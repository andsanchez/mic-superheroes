package com.andsanchez.micsuperheros.infrastructure.persistence;

import com.andsanchez.micsuperheros.infrastructure.persistence.SuperheroEntity;
import com.andsanchez.micsuperheros.infrastructure.persistence.SuperheroEntityMapper;
import com.andsanchez.micsuperheros.mother.SuperheroEntityMother;
import com.andsanchez.micsuperheros.mother.SuperheroMother;
import com.andsanchez.micsuperheros.domain.Superhero;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

class SuperheroEntityMapperTest {

    private SuperheroEntityMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new SuperheroEntityMapper(new ModelMapper());
    }

    @Test
    void entityToSuperHero() {
        SuperheroEntity superheroEntity = SuperheroEntityMother.create();

        Superhero superhero = mapper.entityToSuperHero(superheroEntity);

        Assertions.assertThat(superhero).isNotNull()
                .hasNoNullFieldsOrProperties()
                .returns(superheroEntity.getId(), Superhero::getId)
                .returns(superheroEntity.getName(), Superhero::getName);
    }

    @Test
    void superheroToEntity() {
        Superhero superhero = SuperheroMother.create();

        SuperheroEntity superheroEntity = mapper.superheroToEntity(superhero);

        Assertions.assertThat(superheroEntity).isNotNull()
                .hasNoNullFieldsOrProperties()
                .returns(superhero.getId(), SuperheroEntity::getId)
                .returns(superhero.getName(), SuperheroEntity::getName);
    }
}