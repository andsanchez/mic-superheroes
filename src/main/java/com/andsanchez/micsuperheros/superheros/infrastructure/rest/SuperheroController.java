package com.andsanchez.micsuperheros.superheros.infrastructure.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SuperheroController implements SuperheroApi {

    @Override
    public ResponseEntity<List<SuperheroDto>> getSuperheros() {
        return SuperheroApi.super.getSuperheros();
    }

    @Override
    public ResponseEntity<SuperheroDto> createSuperhero(SuperheroDto superheroDto) {
        return SuperheroApi.super.createSuperhero(superheroDto);
    }

    @Override
    public ResponseEntity<Void> deleteSuperhero(Long id) {
        return SuperheroApi.super.deleteSuperhero(id);
    }

    @Override
    public ResponseEntity<List<SuperheroDto>> findSuperheros(String name) {
        return SuperheroApi.super.findSuperheros(name);
    }

    @Override
    public ResponseEntity<SuperheroDto> getSuperheroById(Long id) {
        return SuperheroApi.super.getSuperheroById(id);
    }

    @Override
    public ResponseEntity<SuperheroDto> updateSuperhero(Long id, SuperheroDto superheroDto) {
        return SuperheroApi.super.updateSuperhero(id, superheroDto);
    }
}
