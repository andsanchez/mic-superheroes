package com.andsanchez.micsuperheros.superheros.application.services;

import com.andsanchez.micsuperheros.superheros.infrastructure.rest.SuperheroDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SuperheroService {

    private final List<SuperheroDto> superheroes = new ArrayList<>(List.of(
            new SuperheroDto(1L, "Superman"),
            new SuperheroDto(2L, "Joker")
    ));

    public List<SuperheroDto> getAllSuperheros() {
        return superheroes;
    }

    public SuperheroDto createSuperhero(SuperheroDto superheroDto) {
        superheroDto.setId(generateUniqueId());
        superheroes.add(superheroDto);
        return superheroDto;
    }

    public void deleteSuperhero(Long id) {
        superheroes.removeIf(superhero -> superhero.getId().equals(id));
    }

    public List<SuperheroDto> findSuperherosByName(String name) {
        return superheroes.stream()
            .filter(superhero -> superhero.getName().toLowerCase().contains(name.toLowerCase()))
            .collect(Collectors.toList());
    }

    public SuperheroDto getSuperheroById(Long id) {
        return superheroes.stream()
            .filter(superhero -> superhero.getId().equals(id))
            .findFirst()
            .orElse(null);
    }

    public SuperheroDto updateSuperhero(Long id, SuperheroDto updatedSuperheroDto) {
        SuperheroDto existingSuperhero = getSuperheroById(id);
        if (existingSuperhero != null) {
            existingSuperhero.setName(updatedSuperheroDto.getName());
            return existingSuperhero;
        }
        return null;
    }

    // Simulación de generación de ID único
    private Long generateUniqueId() {
        return (long) (superheroes.size() + 1);
    }
}