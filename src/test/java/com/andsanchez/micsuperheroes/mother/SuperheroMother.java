package com.andsanchez.micsuperheroes.mother;

import com.andsanchez.micsuperheroes.domain.Superhero;
import net.datafaker.Faker;

public class SuperheroMother {

    public static Superhero create() {
        return create(1L);
    }

    public static Superhero create(long id) {
        return new Superhero(id, new Faker().superhero().name());
    }

}
