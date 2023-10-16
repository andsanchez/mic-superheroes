package com.andsanchez.micsuperheros.infrastructure.rest;

import com.andsanchez.micsuperheros.superheros.infrastructure.rest.SuperheroDto;
import com.andsanchez.micsuperheros.superheros.infrastructure.rest.SuperheroRequestDto;
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
                .hasSize(3)
                .extracting("name")
                .containsExactly("Superman", "Manolito el fuerte", "Hulk");
    }

    @Test
    public void getSuperherosByName() {
        String nameToSearch = "man";
        String url = "/v1/superheros?name=" + nameToSearch;

        ResponseEntity<SuperheroDto[]> response = template.getForEntity(url, SuperheroDto[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(List.of(response.getBody()))
                .hasSize(2)
                .extracting("name")
                .containsExactly("Superman", "Manolito el fuerte");
    }

    @Test
    public void createSuperhero() {
        SuperheroRequestDto superheroRequestDto = new SuperheroRequestDto().name("Hulk");

        ResponseEntity<String> response = template.postForEntity("/v1/superheros", superheroRequestDto, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void createSuperhero_BadRequest() {
        SuperheroRequestDto superheroRequestDto = new SuperheroRequestDto();

        ResponseEntity<String> response = template.postForEntity("/v1/superheros", superheroRequestDto, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void deleteSuperhero() {
        Long superheroIdToDelete = 2L;

        ResponseEntity<String> response = template.exchange("/v1/superheros/{id}", HttpMethod.DELETE,
                null, String.class, superheroIdToDelete);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    public void deleteSuperhero_NotFound() {
        Long nonExistingSuperheroIdToDelete = 88L;

        ResponseEntity<String> response = template.exchange("/v1/superheros/{id}", HttpMethod.DELETE,
                null, String.class, nonExistingSuperheroIdToDelete);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void getSuperheroById() {
        Long superheroId = 1L;

        ResponseEntity<SuperheroDto> response = template.getForEntity("/v1/superheros/{id}",
                SuperheroDto.class, superheroId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull()
                .returns(1L, SuperheroDto::getId)
                .returns("Superman", SuperheroDto::getName);
    }

    @Test
    public void getSuperheroById_NotFound() {
        Long nonExistingSuperheroId = 999L;

        ResponseEntity<SuperheroDto> response = template.getForEntity("/v1/superheros/{id}",
                SuperheroDto.class, nonExistingSuperheroId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void updateSuperhero() {
        Long superheroIdToUpdate = 1L;
        SuperheroRequestDto updateSuperheroRequestDto = new SuperheroRequestDto().name("Updated Superman");

        ResponseEntity<SuperheroDto> response = template.exchange("/v1/superheros/{id}", HttpMethod.PUT,
                new HttpEntity<>(updateSuperheroRequestDto), SuperheroDto.class, superheroIdToUpdate);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull()
                .returns(superheroIdToUpdate, SuperheroDto::getId)
                .returns(updateSuperheroRequestDto.getName(), SuperheroDto::getName);
    }

    @Test
    public void updateSuperhero_NotFound() {
        Long nonExistingSuperheroIdToUpdate = 77L;
        SuperheroRequestDto updateSuperheroRequestDto = new SuperheroRequestDto().name("Updated Superhero Not Found");

        ResponseEntity<SuperheroDto> response = template.exchange("/v1/superheros/{id}", HttpMethod.PUT,
                new HttpEntity<>(updateSuperheroRequestDto), SuperheroDto.class, nonExistingSuperheroIdToUpdate);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

}