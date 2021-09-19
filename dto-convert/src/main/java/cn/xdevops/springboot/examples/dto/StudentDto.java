package cn.xdevops.springboot.examples.dto;

import cn.xdevops.springboot.examples.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    private Long id;
    private String firstName;
    private String lastName;
    private int age;

    public StudentDto(Student student) {
        this.id = student.getId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.age = student.getAge();
    }

    public Student toEntity() {
        Student student = new Student();
        student.setId(this.id);
        student.setFirstName(this.getFirstName());
        student.setLastName(this.getLastName());
        student.setAge(this.getAge());
        return student;
    }
}
