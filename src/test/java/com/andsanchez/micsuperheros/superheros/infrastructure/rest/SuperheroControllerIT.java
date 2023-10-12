package com.andsanchez.micsuperheros.superheros.infrastructure.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SuperheroControllerIT {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void getSuperheros() {
        ResponseEntity<SuperheroDto[]> response = template.getForEntity("/v1/superheros", SuperheroDto[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(List.of(response.getBody()))
                .hasSize(2)
                .extracting("name")
                .containsExactly("Superman", "Joker");
    }

    @Test
    public void getSuperherosByName() {
        String nameToSearch = "man";
        String url = "/v1/superheros?name=" + nameToSearch;

        ResponseEntity<SuperheroDto[]> response = template.getForEntity(url, SuperheroDto[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(List.of(response.getBody()))
                .hasSize(1)
                .first()
                .returns("Superman", SuperheroDto::getName);
    }

    @Test
    public void createSuperhero() {
        // Preparar los datos para crear un superhéroe
        SuperheroDto superheroDto = new SuperheroDto();

        ResponseEntity<String> response = template.postForEntity("/v1/superheros", superheroDto, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void deleteSuperhero() {
        // Supongamos que tienes un ID válido para eliminar un superhéroe
        Long superheroIdToDelete = 1L; // Reemplaza con un ID válido

        ResponseEntity<String> response = template.exchange("/v1/superheros/{id}", HttpMethod.DELETE, null, String.class, superheroIdToDelete);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    public void getSuperheroById() {
        Long superheroId = 1L;

        ResponseEntity<SuperheroDto> response = template.getForEntity("/v1/superheros/{id}", SuperheroDto.class, superheroId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull()
                .returns(1L, SuperheroDto::getId)
                .returns("Superman", SuperheroDto::getName);
    }

    @Test
    public void updateSuperhero() {
        Long superheroIdToUpdate = 1L;
        SuperheroDto updatedSuperheroDto = new SuperheroDto();

        ResponseEntity<String> response = template.exchange("/v1/superheros/{id}", HttpMethod.PUT, new HttpEntity<>(updatedSuperheroDto), String.class, superheroIdToUpdate);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}