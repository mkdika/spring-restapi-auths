# JWT Authentication

This is Spring Boot REST API JSON Web Token (JWT) demo.
In order to have a JWT token and use for each request, we need to get authenticate first (login).

### Pre-requirement

- JDK 8

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
java -jar jwtauth-0.0.1-SNAPSHOT.jar
```

Web REST API can be access from port: `8080`.

### Test REST API (Local)

Use `curl`:

Login:

```console
# username: foo, password: bar
curl -i -d "{\"username\": \"foo\",\"password\": \"bar\"}" -H "Content-Type: application/json" -X POST http://localhost:8080/login
```

Then you will get the result, attach this response `Authorization` value with each request:

![Imgur](https://i.imgur.com/QNHrRU6.png)

```console
# request example
curl -H "Authorization: BearereyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJjYTBkZWYwOGVmZGU4ZGI2NzViZTY2NzYxOGM2ZDEwMSIsInN1YiI6ImZvbyIsImlhdCI6MTUzNzEwMjg5NywiZXhwIjoxNTM4NDE2ODk3LCJhdXRob3JpdGllcyI6W3siYXV0aG9yaXR5IjoiUk9MRV9ERUZBVUxUIn1dfQ.fqDnvfcW-HU6QVzeFy2fg04WhlHEg4vVbZc5qBBPrWOSilriZHYmEuUssf28suINVZRhEUPc33vqw5UADxKe8w" http://localhost:8080/echo/maikel

# will return: maikel
```

__Enjoy!__ :heart: