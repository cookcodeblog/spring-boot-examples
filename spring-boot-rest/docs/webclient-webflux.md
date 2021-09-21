[TOC]

# WebFlux WebClient

While the Mono is used for handling zero or one result,
the Flux is used to handle zero to many results,
possibly even infinite results.

Project Reactor has introduced two implementations of Publisher: Mono and Flux.

Flux<T> is useful when we need to handle zero to many or potentially infinite results. We can think of a Twitter feed as an example.

When we know that the results are returned all at once – as in our use case – we can use Mono<T>.


## Timeout

The Response Timeout defines the maximum time a client, after sending a request, waits to receive the response. With WebClient, we can set this timeout specifically for a particular request or globally while creating a WebClient instance.

Similar to the global response timeout we can set read timeout and write timeout on the HttpClient. First, let’s understand what these timeouts are. The Read timeout triggers when a no data is read within the specified time period. Similarly, Write timeout triggers when a write operation does not finished in the specified time.

The Connection timeout happens when connection between a client and a server is not established within the specified time.

- Reactive Timeout (basic timeout)
- Response Timeout
- Read Timeout
- Write Timeout
- Connection Timeout
- TLS Timeout



## References

- <https://www.baeldung.com/spring-5-webclient>
- <https://howtodoinjava.com/spring-webflux/webclient-get-post-example/>
- <https://dimitr.im/difference-between-mono-and-flux>
- <https://stackoverflow.com/questions/47988433/mono-vs-flux-in-reactive-stream>
- <https://reflectoring.io/spring-webclient/>
- <https://www.baeldung.com/spring-webclient-json-list>
- <https://www.amitph.com/spring-webclient-read-json-data/>
- <https://www.amitph.com/spring-webflux-timeouts/>