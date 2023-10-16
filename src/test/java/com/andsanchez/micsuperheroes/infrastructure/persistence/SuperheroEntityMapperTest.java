package com.andsanchez.micsuperheroes.infrastructure.persistence;

import com.andsanchez.micsuperheroes.domain.Superhero;
import com.andsanchez.micsuperheroes.mother.SuperheroEntityMother;
import com.andsanchez.micsuperheroes.mother.SuperheroMother;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class SuperheroEntityMapperTest {

    private final SuperheroEntityMapper mapper = new SuperheroEntityMapper();

    @Test
    void entityToSuperHero() {
        SuperheroEntity superheroEntity = SuperheroEntityMother.create();

        Superhero superhero = mapper.entityToSuperHero(superheroEntity);

        Assertions.assertThat(superhero).isNotNull()
                .hasNoNullFieldsOrProperties()
                .returns(superheroEntity.getId(), Superhero::id)
                .returns(superheroEntity.getName(), Superhero::name);
    }

    @Test
    void superheroToEntity() {
        Superhero superhero = SuperheroMother.create();

        SuperheroEntity superheroEntity = mapper.superheroToEntity(superhero);

        Assertions.assertThat(superheroEntity).isNotNull()
                .returns(superhero.id(), SuperheroEntity::getId)
                .returns(superhero.name(), SuperheroEntity::getName);
    }
}