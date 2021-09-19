package cn.xdevops.springboot.examples.payroll.controller;

import cn.xdevops.springboot.examples.payroll.dto.EmployeeDto;
import cn.xdevops.springboot.examples.payroll.entity.Employee;
import cn.xdevops.springboot.examples.payroll.exception.EmployeeNotFoundException;
import cn.xdevops.springboot.examples.payroll.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dto/employees")
public class DtoConvertEmployeeController {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public DtoConvertEmployeeController(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<EmployeeDto> findAll() {
        return employeeRepository.findAll().stream()
                .map(employee -> modelMapper.map(employee, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    public EmployeeDto addEmployee(@RequestBody EmployeeDto employeeDto) {
        return modelMapper.map(employeeRepository.save(modelMapper.map(employeeDto, Employee.class)),
                EmployeeDto.class);
    }

    @GetMapping("/{id}")
    public EmployeeDto findOne(@PathVariable Long id) {
        return modelMapper.map(employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id)),
                EmployeeDto.class);
    }

    @PutMapping("/{id}")
    public EmployeeDto updateEmployee(@RequestBody EmployeeDto newEmployeeDto, @PathVariable Long id) {
        // Update existing employee otherwise create a new employee
        Employee newEmployee = modelMapper.map(newEmployeeDto, Employee.class);
        return modelMapper.map(employeeRepository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return employeeRepository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return employeeRepository.save(newEmployee);
                }), EmployeeDto.class);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
    }
}
