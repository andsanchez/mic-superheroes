package com.andsanchez.micsuperheros.superheros.infrastructure.rest;

import com.andsanchez.micsuperheros.superheros.domain.Superhero;
import com.andsanchez.micsuperheros.superheros.domain.SuperheroService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<SuperheroDto> createSuperhero(SuperheroDto superheroDto) {
        Superhero superhero = mapper.dtoToSuperhero(superheroDto);
        Superhero createdSuperhero = service.createSuperhero(superhero);
        SuperheroDto createdSuperheroDto = mapper.superheroToDto(createdSuperhero);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSuperheroDto);
    }

    @Override
    public ResponseEntity<SuperheroDto> getSuperheroById(Long id) {
        return Optional.of(service.getSuperheroById(id))
                .map(mapper::superheroToDto)
                .map(superheroDto ->  ResponseEntity.status(HttpStatus.OK).body(superheroDto))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<SuperheroDto> updateSuperhero(Long id, SuperheroDto superheroDto) {
        Superhero superhero = mapper.dtoToSuperhero(superheroDto);
        //FIXME ASR
        superhero.setId(id);

        Superhero updatedSuperhero = service.updateSuperhero(superhero);

        if (updatedSuperhero != null) {
            SuperheroDto updatedSuperheroDto = mapper.superheroToDto(updatedSuperhero);
            return ResponseEntity.status(HttpStatus.OK).body(updatedSuperheroDto);
        } else {
            return ResponseEntity.notFound().build(); // Code 404 Not Found
        }
    }

    @Override
    public ResponseEntity<Void> deleteSuperhero(Long id) {
        if (service.deleteSuperhero(id)) {
            return ResponseEntity.noContent().build(); // Code 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // Code 404 Not Found
        }
    }

}
