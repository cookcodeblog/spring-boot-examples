package cn.xdevops.springboot.examples.entity;

import cn.xdevops.springboot.examples.dto.StudentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private int age;

    public Student(StudentDto studentDto) {
        this.id = studentDto.getId();
        this.firstName = studentDto.getFirstName();
        this.lastName = studentDto.getLastName();
        this.age = studentDto.getAge();
    }

    public StudentDto toDto() {
        StudentDto studentDto  = new StudentDto();
        studentDto.setId(this.id);
        studentDto.setFirstName(this.firstName);
        studentDto.setLastName(this.lastName);
        studentDto.setAge(this.age);
        return studentDto;
    }
}
