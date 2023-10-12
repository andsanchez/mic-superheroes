package com.andsanchez.micsuperheros.superheros.infrastructure.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SuperheroController implements SuperheroApi {


    @Override
    public ResponseEntity<List<SuperheroDto>> getSuperheros(String name) {
        // TODO Implementa la lógica para obtener los superheros o buscar por nombre
        // Implementación temporal con datos fictios
        List<SuperheroDto> superheros = new ArrayList<>();
        superheros.add(new SuperheroDto(1L, "Superman"));
        if ("man".equals(name)){
            return new ResponseEntity<>(superheros, HttpStatus.OK);
        }
        superheros.add(new SuperheroDto(2L, "Joker"));
        return new ResponseEntity<>(superheros, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SuperheroDto> createSuperhero(SuperheroDto superheroDto) {
        // TODO Implementa la lógica para crear un nuevo superhéroe a partir de superheroDto
        // Implementación temporal que simplemente devuelve el mismo objeto recibido
        return new ResponseEntity<>(superheroDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteSuperhero(Long id) {
        // TODO Implementa la lógica para eliminar un superhéroe por su ID
        // Implementación temporal que no realiza ninguna acción
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @Override
    public ResponseEntity<SuperheroDto> getSuperheroById(Long id) {
        // TODO Implementa la lógica para obtener un superhéroe por su ID
        // Implementación temporal que devuelve un superhéroe ficticio por ID
        if (id == 1L) {
            return new ResponseEntity<>(new SuperheroDto(1L, "Superman"), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<SuperheroDto> updateSuperhero(Long id, SuperheroDto superheroDto) {
        // TODO Implementa la lógica para actualizar un superhéroe por su ID con los datos proporcionados en superheroDto
        // Implementación temporal que simplemente devuelve el mismo objeto recibido
        return new ResponseEntity<>(superheroDto, HttpStatus.OK);
    }
}
