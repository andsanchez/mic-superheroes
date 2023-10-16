package com.andsanchez.micsuperheros.mother;

import com.andsanchez.micsuperheros.domain.Superhero;
import net.datafaker.Faker;

public class SuperheroMother {

    public static Superhero create() {
        return create(1L);
    }

    public static Superhero create(long id) {
        return new Superhero(id, new Faker().superhero().name());
    }

}
