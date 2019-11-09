# Training Spring Boot Application
This repository contains the CRUD application. You can easily use it to create a list of training courses. Add lessons, tasks for them and links to solutions to help your colleagues.

## Features
- CRUD courses, lessons and tasks throught admin dashboard
- One Course can have Many Lessons
- One Lesson can have Many Tasks

## Future Features
- Registration form and own lists of courses for users
- REST API

## Using
- Spring Boot 2
  - Spring Security
  - Spring MVC
  - Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- Thymeleaf

## Do not forget
Add file `~\src\main\resources\application.properties` like:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/<database_name>
spring.datasource.username=<username>
spring.datasource.password=<password>

spring.jpa.hibernate.ddl-auto=update
```

## Build
`mvn spring-boot:run`
