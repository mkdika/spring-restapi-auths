# Basic Authentication

This is Spring Boot REST API Basic Authentication demo.
Basic authentication is not suggested to use under non-SSL communication,
due to the authentication data are not encrypted and only encode by Base64.

### Pre-requirement

- JDK 8
- Maven3

### Build the Project

```console
mvnw clean package 
```

### Test the Project (Automatic)

```console
mvnw clean test
```

### Run the Project

From maven:

```console
mvnw spring-boot:run
```

or, after `mvn clean package`, execute the file from `/target` folder:

```console
java -jar basicauth-0.0.1-SNAPSHOT.jar
```

Web REST API can be access from port: `8080`.

### Test REST API (Local)

Use `curl`:

```console
curl -u foo:bar http://localhost:8080/echo/maikel
# will return: maikel
```

Default username:`foo`, password: `bar`

__Enjoy!__ :heart: