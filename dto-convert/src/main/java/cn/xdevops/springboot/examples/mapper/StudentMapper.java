package cn.xdevops.springboot.examples.mapper;

import cn.xdevops.springboot.examples.dto.StudentDto;
import cn.xdevops.springboot.examples.entity.Student;

public class StudentMapper {

    public Student toEntity(StudentDto studentDto) {
        Student student = new Student();
        student.setId(studentDto.getId());
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setAge(studentDto.getAge());
        return student;
    }

    public StudentDto toDto(Student student) {
        StudentDto studentDto  = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setFirstName(student.getFirstName());
        studentDto.setLastName(student.getLastName());
        studentDto.setAge(student.getAge());
        return studentDto;
    }
}
