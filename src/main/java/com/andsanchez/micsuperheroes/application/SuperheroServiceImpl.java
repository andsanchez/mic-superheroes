package com.andsanchez.micsuperheroes.application;

import com.andsanchez.micsuperheroes.domain.Superhero;
import com.andsanchez.micsuperheroes.domain.SuperheroRepository;
import com.andsanchez.micsuperheroes.domain.SuperheroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SuperheroServiceImpl implements SuperheroService {

    private final SuperheroRepository superheroRepository;

    @Override
    public Superhero createSuperhero(Superhero superhero) {
        return superheroRepository.save(superhero);
    }

    @Override
    public Superhero getSuperheroById(Long id) {
        return superheroRepository.findById(id);
    }

    @Override
    public List<Superhero> getAllSuperheroes() {
        return superheroRepository.findAll();
    }

    @Override
    public List<Superhero> findSuperheroesByNameContainingIgnoreCase(String name) {
        return superheroRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public Superhero updateSuperhero(Superhero superhero) {
        Superhero existingSuperhero = superheroRepository.findById(superhero.id());
        Superhero updatedSuperhero = new Superhero(existingSuperhero.id(), superhero.name());
        return superheroRepository.save(updatedSuperhero);
    }

    @Override
    public void deleteSuperhero(Long superheroId) {
        Superhero superheroToDelete = superheroRepository.findById(superheroId);
        superheroRepository.delete(superheroToDelete);
    }
}