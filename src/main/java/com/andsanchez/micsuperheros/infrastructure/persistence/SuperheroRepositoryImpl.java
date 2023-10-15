package com.andsanchez.micsuperheros.infrastructure.persistence;

import com.andsanchez.micsuperheros.domain.Superhero;
import com.andsanchez.micsuperheros.domain.SuperheroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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
    //TODO ASR Lazanar exception propia
    public Superhero findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::entityToSuperHero)
                .orElseThrow();
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
        Optional.of(superhero)
                .map(mapper::superheroToEntity)
                .ifPresent(jpaRepository::delete);
    }
}
