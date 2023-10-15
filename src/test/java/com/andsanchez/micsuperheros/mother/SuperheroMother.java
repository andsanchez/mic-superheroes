package com.andsanchez.micsuperheros.mother;

import com.andsanchez.micsuperheros.superheros.domain.Superhero;
import net.datafaker.Faker;

public class SuperheroMother {

    private static final Faker FAKER = new Faker();

    public static Superhero create() {
        return create(1L);
    }

    public static Superhero create(long id) {
        Superhero superhero = new Superhero();
        superhero.setId(id);
        superhero.setName(FAKER.superhero().name());
        return superhero;
    }

}
