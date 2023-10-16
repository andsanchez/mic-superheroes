package com.andsanchez.micsuperheroes.infrastructure.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
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

    private static final String PATH_SUPERHEROES = "/v1/superheroes";

    private static final String PATH_SUPERHEROES_ID = "/v1/superheroes/{id}";

    public static final String PATH_SUPERHEROES_NAME = "/v1/superheroes?name=";

    private static final SuperheroRequestDto BAD_SUPERHERO_REQUEST_DTO_NO_NAME = new SuperheroRequestDto();

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getSuperheros() {
        ResponseEntity<SuperheroDto[]> response = template.getForEntity(PATH_SUPERHEROES, SuperheroDto[].class);

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
        String url = PATH_SUPERHEROES_NAME + nameToSearch;

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

        ResponseEntity<String> response = template.postForEntity(PATH_SUPERHEROES, superheroRequestDto, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void createSuperhero_BadRequest() throws JsonProcessingException {
        ResponseEntity<String> response = template.postForEntity(PATH_SUPERHEROES,
                BAD_SUPERHERO_REQUEST_DTO_NO_NAME, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertBadRequestError(response);
    }

    @Test
    public void deleteSuperhero() {
        Long superheroIdToDelete = 2L;

        ResponseEntity<String> response = template.exchange(PATH_SUPERHEROES_ID, HttpMethod.DELETE,
                null, String.class, superheroIdToDelete);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    public void deleteSuperhero_NotFound() throws JsonProcessingException {
        Long nonExistingSuperheroIdToDelete = 88L;

        ResponseEntity<String> response = template.exchange(PATH_SUPERHEROES_ID, HttpMethod.DELETE,
                null, String.class, nonExistingSuperheroIdToDelete);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertSuperheroNotFoundError(response, nonExistingSuperheroIdToDelete);
    }

    @Test
    public void getSuperheroById() {
        Long superheroId = 1L;

        ResponseEntity<SuperheroDto> response = template.getForEntity(PATH_SUPERHEROES_ID,
                SuperheroDto.class, superheroId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull()
                .returns(1L, SuperheroDto::getId)
                .returns("Superman", SuperheroDto::getName);
    }

    @Test
    public void getSuperheroById_NotFound() throws JsonProcessingException {
        Long nonExistingSuperheroId = 999L;

        ResponseEntity<String> response = template.getForEntity(PATH_SUPERHEROES_ID,
                String.class, nonExistingSuperheroId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertSuperheroNotFoundError(response, nonExistingSuperheroId);
    }

    @Test
    public void updateSuperhero() {
        Long superheroIdToUpdate = 1L;
        SuperheroRequestDto updateSuperheroRequestDto = new SuperheroRequestDto().name("Updated Superman");

        ResponseEntity<SuperheroDto> response = template.exchange(PATH_SUPERHEROES_ID, HttpMethod.PUT,
                new HttpEntity<>(updateSuperheroRequestDto), SuperheroDto.class, superheroIdToUpdate);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull()
                .returns(superheroIdToUpdate, SuperheroDto::getId)
                .returns(updateSuperheroRequestDto.getName(), SuperheroDto::getName);
    }

    @Test
    public void updateSuperhero_BadRequest() throws JsonProcessingException {
        Long superheroIdToUpdate = 1L;

        ResponseEntity<String> response = template.exchange(PATH_SUPERHEROES_ID, HttpMethod.PUT,
                new HttpEntity<>(BAD_SUPERHERO_REQUEST_DTO_NO_NAME), String.class, superheroIdToUpdate);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertBadRequestError(response);
    }

    @Test
    public void updateSuperhero_NotFound() throws JsonProcessingException {
        Long nonExistingSuperheroIdToUpdate = 77L;
        SuperheroRequestDto updateSuperheroRequestDto = new SuperheroRequestDto().name("Updated Superhero Not Found");

        ResponseEntity<String> response = template.exchange(PATH_SUPERHEROES_ID, HttpMethod.PUT,
                new HttpEntity<>(updateSuperheroRequestDto), String.class, nonExistingSuperheroIdToUpdate);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertSuperheroNotFoundError(response, nonExistingSuperheroIdToUpdate);
    }

    private JsonNode getAndAssertJsonNodeRoot(ResponseEntity<String> response) throws JsonProcessingException {
        assertThat(response.getBody()).isNotNull();
        JsonNode root = objectMapper.readTree(response.getBody());
        Assertions.assertThat(root).isNotNull();
        return root;
    }

    private void assertSuperheroNotFoundError(ResponseEntity<String> response, Long superheroId) throws JsonProcessingException {
        JsonNode root = getAndAssertJsonNodeRoot(response);
        assertThat(root.path("title").asText()).isEqualTo("Superhero Not Found");
        assertThat(root.path("detail").asText()).isEqualTo("Superhero with id " + superheroId + " not found");
    }

    private void assertBadRequestError(ResponseEntity<String> response) throws JsonProcessingException {
        JsonNode root = getAndAssertJsonNodeRoot(response);
        assertThat(root.path("detail").asText()).isEqualTo("Invalid request content.");
    }
}