package cn.xdevops.springboot.examples.payroll.controller;

import cn.xdevops.springboot.examples.payroll.entity.Employee;
import cn.xdevops.springboot.examples.payroll.exception.EmployeeNotFoundException;
import cn.xdevops.springboot.examples.payroll.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resp/employees")
public class ResponseEntityEmployeeController {

    private final EmployeeRepository employeeRepository;

    public ResponseEntityEmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(employeeRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        // Return HTTP Status Code: Created 201
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(employeeRepository.save(employee));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findOne(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        // Update existing employee otherwise create a new employee
        return ResponseEntity.status(HttpStatus.OK)
                .body(employeeRepository.findById(id)
                    .map(employee -> {
                        employee.setName(newEmployee.getName());
                        employee.setRole(newEmployee.getRole());
                        return employeeRepository.save(employee);
                    })
                    .orElseGet(() -> {
                        newEmployee.setId(id);
                        return employeeRepository.save(newEmployee);
                    }));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // Return HTTP Status Code: No Content 204
    public void deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
    }
}