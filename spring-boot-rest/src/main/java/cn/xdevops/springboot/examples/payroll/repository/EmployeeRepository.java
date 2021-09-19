package cn.xdevops.springboot.examples.payroll.repository;

import cn.xdevops.springboot.examples.payroll.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
