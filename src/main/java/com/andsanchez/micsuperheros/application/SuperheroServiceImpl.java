package com.andsanchez.micsuperheros.application;

import com.andsanchez.micsuperheros.domain.Superhero;
import com.andsanchez.micsuperheros.domain.SuperheroRepository;
import com.andsanchez.micsuperheros.domain.SuperheroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public List<Superhero> findSuperheroesByNameContaining(String name) {
        return superheroRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public Superhero updateSuperhero(Superhero superhero) {
        Superhero existingSuperhero = superheroRepository.findById(superhero.id());
        Superhero updatedSuperhero = new Superhero(existingSuperhero.id(), superhero.name());
        return superheroRepository.save(updatedSuperhero);
    }

    @Override
    public boolean deleteSuperhero(Long superheroId) {
        Optional<Superhero> superheroOptional = Optional.ofNullable(superheroRepository.findById(superheroId));
        if (superheroOptional.isPresent()) {
            superheroRepository.delete(superheroOptional.get());
            return true;
        } else {
            return false;
        }
    }
}