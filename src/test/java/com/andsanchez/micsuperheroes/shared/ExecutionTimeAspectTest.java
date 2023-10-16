package com.andsanchez.micsuperheroes.shared;

import com.andsanchez.micsuperheroes.domain.SuperheroService;
import com.andsanchez.micsuperheroes.infrastructure.rest.SuperheroApi;
import com.andsanchez.micsuperheroes.infrastructure.rest.SuperheroController;
import com.andsanchez.micsuperheroes.infrastructure.rest.SuperheroDtoMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(OutputCaptureExtension.class)
class ExecutionTimeAspectTest {

    private static final String NULL_NAME = null;

    private final SuperheroService superheroService = Mockito.mock(SuperheroService.class);

    @Test
    void measureExecutionTimeDefault(CapturedOutput output) {
        // Arrange - Aspect config and target
        ExecutionTimeAspect executionTimeAspect = new ExecutionTimeAspect();
        SuperheroApi target = new SuperheroController(superheroService, new SuperheroDtoMapper());
        AspectJProxyFactory factory = new AspectJProxyFactory(target);
        factory.addAspect(executionTimeAspect);
        SuperheroApi proxy = factory.getProxy();
        Mockito.when(superheroService.getAllSuperheroes()).thenReturn(Collections.emptyList());

        // Act - Execute target proxy method annotated
        proxy.getSuperheroes(NULL_NAME);

        // Assert - Verify log
        assertThat(output.getOut()).contains("SuperheroApi.getSuperheroes(..) executed in");
    }

}