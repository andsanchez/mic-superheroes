package com.andsanchez.micsuperheroes.shared;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecutionTimeAspect {

    private static final Logger logger = LoggerFactory.getLogger(ExecutionTimeAspect.class);


    @Around("@annotation(executionTimeAnnotation)")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint, ExecutionTime executionTimeAnnotation) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - startTime;

        logMessage(joinPoint, executionTimeAnnotation, executionTime);

        return result;
    }

    private static void logMessage(ProceedingJoinPoint joinPoint, ExecutionTime executionTimeAnnotation, long executionTime) {
        if (executionTime >= executionTimeAnnotation.thresholdMillis()) {
            logger.atLevel(executionTimeAnnotation.logLevel())
                    .log(joinPoint.getSignature().toShortString() + " executed in " + executionTime + "ms");
        }
    }

}
