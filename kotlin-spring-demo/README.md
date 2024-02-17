# Spring Kotlin POC

### Techs
- Java 21
- Spring Boot 3.2.2
- Kotlin 1.9.2
- Maven

### Prerequisite
- Java 21 

### Usage

- Set the java version with [sdkman](https://sdkman.io/)
```shell
sdk env
```

- Build app
```shell
mvn clean install
```

### Requests

- http://localhost:8080/names
- http://localhost:8080/names?name={nameParamValue}
- http://localhost:8080/v1/cars
- Create car
```shell
curl --location 'http://localhost:8080/v1/cars' \
--header 'Content-Type: application/json' \
--data '{
    "model": "Audi TT"
}'
```
- http://localhost:8080/v1/cars/{id}
- http://localhost:8080/people
- Create person
```shell
curl --location 'http://localhost:8080/people' \
  --header 'Content-Type: application/json' \
  --data '{
  "name": "Gabriel"
}'
```
- http://localhost:8080/people/{id}

### Access H2 Database

- H2 Console: http://localhost:8080/h2-console
- JDBC Url: `jdbc:h2:file:./data/testdb`
- User: `name`
- Password: `password`