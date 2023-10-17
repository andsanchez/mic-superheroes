# mic-superheroes

Service for hexagonal architecture for performing CRUD operations on superheroes.

## Requirements

Make sure you have the following components installed before running the application:

- JDK 21 - project built with Oracle [OpenJDK 21](https://jdk.java.net/21/)
- Maven 3.9.2 - project includes Maven Wrapper (mvnw)
- Docker - to run the application in a Docker container.

## Running the application on local environment
There are several ways to run a Spring Boot application on your local machine.

### Running with Maven

To run the application using Maven, follow these steps from your command line:

```bash
# Clone this repository
$ git clone https://github.com/andsanchez/mic-superheroes.git

# Go into the repository
$ cd mic-superheroes

# Run the app
$ ./mvnw spring-boot:run
```

The application will start, and you can access it at http://localhost:8080/actuator/health to check its status.

### Running with Docker
To run the application in a Docker container, make sure you have Docker installed on your system and follow these steps from your command line:

```bash
# Clone this repository
$ git clone https://github.com/andsanchez/mic-superheroes.git

# Go into the repository
$ cd mic-superheroes

# Build the Docker image
$ docker build -t mic-superheroes:latest .

# Once the image build is complete, run the app
$ docker run -p 8080:8080 mic-superheroes:latest
```

You can check the startup of the application at: http://localhost:8080/actuator/health

The `Dockerfile` is used to build a Docker image. The application runs in a Docker container based on Eclipse Temurin.

## API Documentation

The `src/main/resources/spec/superheroes-openapi-rest.yml` file defines the API specification using OpenAPI. It describes the available endpoints, routes, and data models used.

You can access the automatically generated API documentation via Springdoc OpenAPI.

Once the application is running:
- The Swagger UI page will then be available at http://localhost:8080/swagger-ui.html
- The OpenAPI description will be available for json format at: http://localhost:8080/v3/api-docs and is available in yaml format as well, on the path: /v3/api-docs.yaml

### Important note
Thanks to the `openapi-generator-maven-plugin` configuration in the application's pom.xml, the API controller and DTO model are automatically generated in the target directory. The generated files are `SuperheroApi.java` (implemented by `SuperheroController.java`) and `SuperheroDto.java`

Here are the advantages of this approach:
- API-First: It allows you to focus on building the API specification first and then use it in the application itself.
- Postman Collections: You can also generate Postman collections directly from the specification. You can find a Postman collection added to the project at: `src/main/resources/postman`

## API Quickstart

You can use the postman collection included in the directory `src/main/resources/postman` to test the api.

## Technologies dependencies

Here are the most important dependencies:
- Spring Boot 3.1.4
- Spring Boot Web
- Spring Boot Data JPA
- Spring Boot Validation
- Springdoc OpenAPI
- H2 Database
- Flyway Database Migrations
- Lombok

## Latest Version Utilities
This project leverages several utilities and features from the latest versions of the technologies used:

- **Transition from Java EE to Jakarta EE:** This project embraces the transition from Java EE to Jakarta EE, ensuring compatibility with modern Java EE standards and specifications. Jakarta EE is the new home for cloud-native Java, making it a robust choice for building enterprise applications.

- **Unit Test Aspect with AspectJProxyFactory & OutputCaptureExtension:** To enhance unit testing, this project makes use of AspectJProxyFactory to create proxy instances of classes and methods. It also utilizes OutputCaptureExtension, which captures console output for testing purposes.

- **Java Record:** Java 16 introduced Records, a compact way to declare classes that are immutable data carriers. Records simplify code and enhance readability by reducing boilerplate code for data classes. This project takes advantage of Java Records for cleaner and more concise data representation.

- **Support the RFC 7807 with Spring "Problem Details":** This project adheres to RFC 7807, which defines a standard for providing problem details in HTTP API responses. The Spring Framework's "Problem Details" support is employed to ensure consistent and informative error responses, making it easier for clients to understand and handle errors.

- **Java 16's toList:** Java 16 introduced the toList collector, which simplifies the process of collecting stream elements into an immutable List. This feature is used in this project to enhance code efficiency and readability when working with streams.

By incorporating these latest version utilities, this project stays current with best practices and leverages modern features to provide a robust and efficient application.

------------
Enjoy working with your Superheroes API!

If you have any questions or need further assistance, please don't hesitate to ask.