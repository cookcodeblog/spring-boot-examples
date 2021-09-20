package cn.xdevops.springboot.examples.payroll.mapper;

import cn.xdevops.springboot.examples.payroll.dto.EmployeeDto;
import cn.xdevops.springboot.examples.payroll.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    EmployeeDto toEmployeeDto(Employee employee);

    Employee toEmployee(EmployeeDto employeeDto);
}
