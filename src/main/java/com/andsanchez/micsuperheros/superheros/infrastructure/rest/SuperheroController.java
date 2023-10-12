package com.andsanchez.micsuperheros.superheros.infrastructure.rest;

import com.andsanchez.micsuperheros.superheros.application.services.SuperheroService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SuperheroController implements SuperheroApi {

    private final SuperheroService superheroService;

    @Override
    public ResponseEntity<List<SuperheroDto>> getSuperheros(String name) {
        List<SuperheroDto> superheros;
        if (StringUtils.isNotEmpty(name)){
            superheros = superheroService.findSuperherosByName(name);
        } else {
            superheros = superheroService.getAllSuperheros();
        }
        return new ResponseEntity<>(superheros, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SuperheroDto> createSuperhero(SuperheroDto superheroDto) {
        SuperheroDto superhero = superheroService.createSuperhero(superheroDto);

        return new ResponseEntity<>(superhero, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteSuperhero(Long id) {
        superheroService.deleteSuperhero(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @Override
    public ResponseEntity<SuperheroDto> getSuperheroById(Long id) {
        SuperheroDto superhero = superheroService.getSuperheroById(id);
        return new ResponseEntity<>(superhero, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SuperheroDto> updateSuperhero(Long id, SuperheroDto superheroDto) {
        SuperheroDto superhero = superheroService.updateSuperhero(id, superheroDto);
        return new ResponseEntity<>(superhero, HttpStatus.OK);
    }
}
