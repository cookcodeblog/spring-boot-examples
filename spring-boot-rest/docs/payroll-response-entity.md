[TOC]

# Payroll with Response Entity

## HTTP Status Code

- 200 OK // Normal success response
- 201 Created // Add a new record
- 204 No Content // Delete a record
- 404 Not Found // Can't find a record

References:
- HTTP Status Codes:  https://www.restapitutorial.com/httpstatuscodes.html


## Using Spring ResponseEntity to Manipulate the HTTP Response

ResponseEntity represents the whole HTTP response: status code, headers, and body.

While ResponseEntity is very powerful, we shouldn't overuse it. 
In simple cases, there are other options that satisfy our needs and they result in much cleaner code.

Usage:
- `ResponseEntity` // HTTP header, status, body
- `@ResponseBody` // HTTP response body
- `@ResponseStatus` // HTTP response status

## API Test

REST API endpoint: http://localhost:8080/resp/employees

## References

- https://www.baeldung.com/spring-response-entity