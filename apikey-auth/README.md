# API Key Authentication

This is Spring Boot REST API Key/Token Authentication demo.
API key authentication will use custom request header, in this case I use `X-Token` and its 
predefine key value.

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
java -jar apikeyauth-0.0.1-SNAPSHOT.jar
```

Web REST API can be access from port: `8080`.

### Test REST API (Local)

Use `curl`:

```console
curl -H "X-Token: C3AB8FF13720E8AD9047DD39466B3C8974E592C2FA383D4A3960714CAEF0C4F2" http://localhost:8080/echo/maikel
# will return: maikel
```

__Enjoy!__ :heart: