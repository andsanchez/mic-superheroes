package com.andsanchez.micsuperheroes.application;

import com.andsanchez.micsuperheroes.domain.Superhero;
import com.andsanchez.micsuperheroes.domain.SuperheroRepository;
import com.andsanchez.micsuperheroes.mother.SuperheroMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SuperheroServiceImplTest {

    private static final Superhero SOME_SUPERHERO = SuperheroMother.create(1L);

    private static final Superhero ANOTHER_SUPERHERO = SuperheroMother.create(2L);

    private static final List<Superhero> SUPERHEROES = List.of(SOME_SUPERHERO, ANOTHER_SUPERHERO);

    private SuperheroServiceImpl superheroService;

    private SuperheroRepository superheroRepository;

    @BeforeEach
    void setUp() {
        superheroRepository = mock(SuperheroRepository.class);
        superheroService = new SuperheroServiceImpl(superheroRepository);
    }

    @Test
    void createSuperhero() {
        // Arrange
        when(superheroRepository.save(SOME_SUPERHERO)).thenReturn(SOME_SUPERHERO);

        // Act
        Superhero result = superheroService.createSuperhero(SOME_SUPERHERO);

        // Assert
        assertThat(result).isEqualTo(SOME_SUPERHERO);
    }

    @Test
    void getSuperheroById() {
        // Arrange
        Long superheroId = 1L;
        when(superheroRepository.findById(superheroId)).thenReturn(SOME_SUPERHERO);

        // Act
        Superhero result = superheroService.getSuperheroById(superheroId);

        // Assert
        assertThat(result).isEqualTo(SOME_SUPERHERO);
    }

    @Test
    void getSuperheroById_NotFound() {
        // Arrange
        Long superheroId = 1L;

//        when(superheroRepository.findById(superheroId)).thenReturn(Optional.empty());

        // Act and Assert
//        assertThrows(RuntimeException.class, () -> superheroService.getSuperheroById(superheroId));
    }

    @Test
    void getAllSuperheroes() {
        // Arrange
        when(superheroRepository.findAll()).thenReturn(SUPERHEROES);

        // Act
        List<Superhero> result = superheroService.getAllSuperheroes();

        // Assert
        assertThat(result).isEqualTo(SUPERHEROES);
    }

    @Test
    void findSuperheroesByNameContainingIgnoreCase() {
        // Arrange
        String searchName = "man";
        when(superheroRepository.findByNameContainingIgnoreCase(searchName)).thenReturn(SUPERHEROES);

        // Act
        List<Superhero> result = superheroService.findSuperheroesByNameContainingIgnoreCase(searchName);

        // Assert
        assertThat(result).isEqualTo(SUPERHEROES);
    }

    @Test
    void updateSuperhero() {
        // Arrange
        Superhero existingSuperhero = new Superhero(1L, "Existing Superhero");
        Superhero updatedSuperhero = new Superhero(1L, "Updated Superhero");
        when(superheroRepository.findById(1L)).thenReturn(existingSuperhero);
        when(superheroRepository.save(updatedSuperhero)).thenReturn(updatedSuperhero);

        // Act
        Superhero result = superheroService.updateSuperhero(updatedSuperhero);

        // Assert
        assertThat(result).isEqualTo(updatedSuperhero);
    }

    @Test
    void deleteSuperhero() {
    }
}