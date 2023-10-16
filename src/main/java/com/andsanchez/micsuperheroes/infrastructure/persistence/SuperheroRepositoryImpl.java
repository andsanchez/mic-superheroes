package com.andsanchez.micsuperheroes.infrastructure.persistence;

import com.andsanchez.micsuperheroes.domain.Superhero;
import com.andsanchez.micsuperheroes.domain.SuperheroNotFoundException;
import com.andsanchez.micsuperheroes.domain.SuperheroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SuperheroRepositoryImpl implements SuperheroRepository {

    private final SuperheroJpaRepository jpaRepository;

    private final SuperheroEntityMapper mapper;

    @Override
    public Superhero save(Superhero superhero) {
        SuperheroEntity entity = mapper.superheroToEntity(superhero);
        SuperheroEntity savedEntity = jpaRepository.save(entity);
        return mapper.entityToSuperHero(savedEntity);
    }

    @Override
    public Superhero findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::entityToSuperHero)
                .orElseThrow(() -> new SuperheroNotFoundException(id));
    }

    @Override
    public List<Superhero> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::entityToSuperHero)
                .toList();
    }

    @Override
    public List<Superhero> findByNameContainingIgnoreCase(String name) {
        return jpaRepository.findByNameContainingIgnoreCase(name).stream()
                .map(mapper::entityToSuperHero)
                .toList();
    }


    @Override
    public void delete(Superhero superhero) {
        SuperheroEntity superheroEntity = mapper.superheroToEntity(superhero);
        jpaRepository.delete(superheroEntity);
    }
}
