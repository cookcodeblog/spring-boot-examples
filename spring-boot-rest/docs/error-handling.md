[TOC]
# Error Handling for Spring REST

## Solutions

1. the Controller-Level `@ExceptionHandler`
2. the HandlerExceptionResolver
  - `ExceptionHandlerExceptionResolver`
  - `DefaultHandlerExceptionResolver`
  - `ResponseStatusExceptionResolver`
  - `SimpleMappingExceptionResolver` and `AnnotationMethodHandlerExceptionResolver`
  - Custom `HandlerExceptionResolver`
3. a global `@ExceptionHandler` with the `@ControllerAdvice` annotation.
4. `ResponseStatusException` (Spring 5 and Above)


## @ControllerAdvice solution

Spring 3.2 brings support for a global @ExceptionHandler with the @ControllerAdvice annotation.

The@ControllerAdvice annotation allows us to consolidate our multiple, scattered @ExceptionHandlers from before into a single, global error handling component.

The actual mechanism is extremely simple but also very flexible:

It gives us full control over the body of the response as well as the status code.
It provides mapping of several exceptions to the same method, to be handled together.
It makes good use of the newer RESTful ResposeEntity response.

One thing to keep in mind here is to match the exceptions declared with @ExceptionHandler to the exception used as the argument of the method.


## ResponseStatusException (Spring 5 and Above)

What are the benefits of using ResponseStatusException?

Excellent for prototyping: We can implement a basic solution quite fast.
One type, multiple status codes: One exception type can lead to multiple different responses. This reduces tight coupling compared to the @ExceptionHandler.
We won't have to create as many custom exception classes.
We have more control over exception handling since the exceptions can be created programmatically.
And what about the tradeoffs?

There's no unified way of exception handling: It's more difficult to enforce some application-wide conventions as opposed to @ControllerAdvice, which provides a global approach.
Code duplication: We may find ourselves replicating code in multiple controllers.
We should also note that it's possible to combine different approaches within one application.

For example, we can implement a @ControllerAdvice globally but also ResponseStatusExceptions locally.

However, we need to be careful: If the same exception can be handled in multiple ways, we may notice some surprising behavior. 
A possible convention is to handle one specific kind of exception always in one way.

## Spring Boot Error Handling

### Default error handling logic

When an exception is thrown, 500 Internal Server Error:

Request:
```bash
http :8080/books/99
```

Response:
```json
{
    "error": "Internal Server Error",
    "message": "Could not find book 99",
    "path": "/books/99",
    "status": 500,
    "timestamp": "2021-09-19T14:00:52.826+00:00",
    "trace": ""
}
```

### Customize HTTP response status code

If a book id not found, it should return a 404 error instead of 500

Let Spring BasicErrorController handle the exception, we just override the status code

Response code is changed to 404.

```json
{
  "error": "Not Found",
  "message": "Could not find book 99",
  "path": "/books/99",
  "status": 404,
  "timestamp": "2021-09-19T14:19:53.623+00:00",
  "trace": ""
}
```

### Customize error response

Customize error response and HTTP status code.

Response:

```json
{
  "errors": null,
  "message": "Could not find book 99",
  "status": 404,
  "timestamp": "2021-09-20 12:04:44"
}
```

### Handle validation errors

Request:
```bash
http :8080/books bookName="DevOps with OpenShift"
```


Response:
```json
{
  "errors": [
    {
      "code": "",
      "description": "Price is mandatory"
    },
    {
      "code": "",
      "description": "Author is mandatory"
    }
  ],
  "message": "Validation failed for arguments",
  "status": 400,
  "timestamp": "2021-09-20 12:05:13"
}
```
Nested errors.


## References
- https://mkyong.com/spring-boot/spring-rest-error-handling-example/
- https://www.baeldung.com/spring-boot-bean-validation
- https://www.baeldung.com/javax-validation
- https://www.baeldung.com/exception-handling-for-rest-with-spring