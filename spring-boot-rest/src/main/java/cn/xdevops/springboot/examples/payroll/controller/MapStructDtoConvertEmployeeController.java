package cn.xdevops.springboot.examples.payroll.controller;

import cn.xdevops.springboot.examples.payroll.dto.EmployeeDto;
import cn.xdevops.springboot.examples.payroll.mapper.EmployeeMapper;
import cn.xdevops.springboot.examples.payroll.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/map/employees")
public class MapStructDtoConvertEmployeeController {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public MapStructDtoConvertEmployeeController(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping
    public List<EmployeeDto> findAll() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::toEmployeeDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public EmployeeDto addEmployee(@RequestBody EmployeeDto employeeDto) {
        return employeeMapper.toEmployeeDto(employeeRepository.save(employeeMapper.toEmployee(employeeDto)));
    }
}
