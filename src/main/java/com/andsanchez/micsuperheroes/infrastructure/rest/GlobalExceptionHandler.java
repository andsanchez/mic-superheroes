package com.andsanchez.micsuperheroes.infrastructure.rest;

import com.andsanchez.micsuperheroes.domain.SuperheroNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    ProblemDetail handleSuperheroNotFoundException(SuperheroNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setTitle("Superhero Not Found");
        return problemDetail;
    }

}
