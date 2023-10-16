package com.andsanchez.micsuperheroes.shared;

import org.slf4j.event.Level;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@code @ExecutionTime} annotation is used to measure the execution time of methods and log it.
 * Users can configure the annotation to specify a minimum execution time threshold for logging
 * and set the log level for the recorded time.
 *
 * @Target(ElementType.METHOD) Indicates that this annotation can be applied to methods.
 * @Retention(RetentionPolicy.RUNTIME) Specifies that this annotation will be available at runtime.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExecutionTime {

   /**
    * Specifies the minimum execution time threshold (in milliseconds) for logging the method execution time.
    * If the method's execution time is greater than or equal to this threshold, it will be logged.
    *
    * @return The minimum execution time threshold in milliseconds. Default is 0, which logs all executions.
    */
   long thresholdMillis() default 0L;

   /**
    * Specifies the log level at which the method execution time will be logged.
    * You can use standard log levels from the SLF4J library, such as Level.INFO, Level.DEBUG, Level.WARN, Level.ERROR, etc.
    *
    * @return The log level for recording the execution time. Default is Level.INFO.
    */
   Level logLevel() default Level.INFO;

}
