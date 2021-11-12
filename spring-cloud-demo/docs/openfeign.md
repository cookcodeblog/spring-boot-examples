[TOC]

# OpenFeign

## Configure Feign Client in Spring Boot

- <Configure Feign Client in Spring Boot>

## Change Default Feign Client implementation in Spring Boot

默认的是JDK自带的HttpClient
可以改为ApacheHttpClient 或 OkHttpClient

## 改为OkHttpClient

application.yaml:

```yaml
feign.okhttp.enabled: true
```

pom.xml

```xml
<dependency>
  <groupId>io.github.openfeign</groupId>
  <artifactId>feign-okhttp</artifactId>
</dependency>
```

Optionally, 进一步在配置类中自定义 `okhttp3.OkHttpClient`

- <https://codingnconcepts.com/spring-boot/change-default-feign-client-implementation/>


## Log level

- NONE, No logging (DEFAULT).
- BASIC, Log only the request method and URL and the response status code and execution time.
- HEADERS, Log the basic information along with request and response headers.
- FULL, Log the headers, body, and metadata for both requests and responses.


## Unit Test for Feign Client

You should always write test cases for your Feign Client. Spring Boot Cloud module spring-cloud-contract 
let you use WireMock in your test cases to mock the API data.

If your Spring Boot application is using default Tomcat embedded server then you can add 
spring-cloud-starter-contract-stub-runner dependency to your maven (or gradle) and add @AutoConfigureWireMock 
at class level in order to use Wiremock in your tests.

Spring Cloud Contract Wiremock:

- <https://docs.spring.io/spring-cloud-contract/docs/current/reference/html/project-features.html#features-wiremock>


Spring Boot 2.4.0 has NoSuchMethod error with WireMock.

After upgrade to Spring Boot 2.4.12 the error is fixed.

WireMock stubbing:
- <http://wiremock.org/docs/stubbing/>


## References

- <https://docs.spring.io/spring-cloud-openfeign/docs/current/reference/html/>
- <https://github.com/ashishlahoti/springboot-examples/tree/main/springboot-api>