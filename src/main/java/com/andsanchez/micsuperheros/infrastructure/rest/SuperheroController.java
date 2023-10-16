package com.andsanchez.micsuperheros.infrastructure.rest;

import com.andsanchez.micsuperheros.domain.Superhero;
import com.andsanchez.micsuperheros.domain.SuperheroService;
import com.andsanchez.micsuperheros.superheros.infrastructure.rest.SuperheroApi;
import com.andsanchez.micsuperheros.superheros.infrastructure.rest.SuperheroDto;
import com.andsanchez.micsuperheros.superheros.infrastructure.rest.SuperheroRequestDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SuperheroController implements SuperheroApi {

    private final SuperheroService service;

    private final SuperheroDtoMapper mapper;

    @Override
    public ResponseEntity<List<SuperheroDto>> getSuperheros(String name) {
        List<Superhero> superheros = StringUtils.isNotEmpty(name) ? service.findSuperheroesByNameContaining(name) : service.getAllSuperheroes();
        List<SuperheroDto> superheroDtos = superheros.stream()
                .map(mapper::superheroToDto)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(superheroDtos);
    }

    @Override
    public ResponseEntity<SuperheroDto> createSuperhero(SuperheroRequestDto superheroRequestDto) {
        Superhero superhero = mapper.createSuperheroRequestDtoToSuperhero(superheroRequestDto);
        Superhero createdSuperhero = service.createSuperhero(superhero);
        SuperheroDto createdSuperheroDto = mapper.superheroToDto(createdSuperhero);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSuperheroDto);
    }

    @Override
    public ResponseEntity<SuperheroDto> getSuperheroById(Long id) {
        Superhero superheroById = service.getSuperheroById(id);
        SuperheroDto superheroDto = mapper.superheroToDto(superheroById);
        return ResponseEntity.status(HttpStatus.OK).body(superheroDto);
    }

    @Override
    public ResponseEntity<SuperheroDto> updateSuperhero(Long id, SuperheroRequestDto superheroRequestDto) {
        Superhero superhero = mapper.updateSuperheroRequestDtoToSuperhero(id, superheroRequestDto);
        Superhero updatedSuperhero = service.updateSuperhero(superhero);
        SuperheroDto updatedSuperheroDto = mapper.superheroToDto(updatedSuperhero);
        return ResponseEntity.status(HttpStatus.OK).body(updatedSuperheroDto);
    }

    @Override
    public ResponseEntity<Void> deleteSuperhero(Long id) {
        service.deleteSuperhero(id);
        return ResponseEntity.noContent().build();
    }

}
