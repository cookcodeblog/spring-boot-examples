[TOC]

# REST API


## HTTP Status Code

Success:
- HTTP 200 OK (GET/POST/PUT/PATCH/DELETE)
- HTTP 201 Created (POST)
- HTTP 204 No Content (DELETE)

Client error:
- HTTP 404 Not Found (GET)
- HTTP 400 Bad Request (POST/PUT, invalid arguments)
- HTTP 401 Unauthorized
- HTTP 403 Forbidden

Server error:
- HTTP 500 Internal Server Error
- HTTP503 Service Unavailable


## References

- [REST with Spring Tutorial](https://www.baeldung.com/rest-with-spring-series)
- [GitHub | Spring Boot REST](https://github.com/eugenp/tutorials/tree/master/spring-boot-rest)
- [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
- <https://github.com/spring-projects/spring-hateoas-examples>
- <https://www.baeldung.com/entity-to-and-from-dto-for-a-java-spring-application>
- <https://www.amitph.com/spring-entity-to-dto/>
- <http://modelmapper.org/getting-started/>
- <https://www.baeldung.com/mapstruct>
- <https://mapstruct.org/>