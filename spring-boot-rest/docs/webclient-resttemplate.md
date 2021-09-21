[TOC]

# RestTemplate Web Client

As of Spring Framework 5, alongside the WebFlux stack, Spring introduced a new HTTP client called WebClient.

WebClient is a modern, alternative HTTP client to RestTemplate. Not only does it provide a traditional synchronous API, but it also supports an efficient nonblocking and asynchronous approach.

That said, if we're developing new applications or migrating an old one, it's a good idea to use WebClient. Moving forward, RestTemplate will be deprecated in future versions.

## Resttemplate features

- GET/POST/PUT/PATCH/DELETE
- Plain JSON string
- POJO
- ResponseEntity
- Client Timeout


## References

- <https://www.baeldung.com/rest-template>
- <https://www.baeldung.com/spring-webclient-resttemplate>
- <https://www.baeldung.com/spring-rest-template-list>
- <https://newbedev.com/spring-resttemplate-timeout>