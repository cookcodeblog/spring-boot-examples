package cn.xdevops.springboot.examples.payroll.exception;

/**
 * When an EmployeeNotFoundException is thrown,
 * this extra tidbit of Spring MVC configuration is used to render an HTTP 404:
 */
public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}
