package cn.xdevops.springboot.examples.dto;

import cn.xdevops.springboot.examples.entity.Student;
import cn.xdevops.springboot.examples.mapper.StudentMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;


class StudentDtoConvertTest {

    private final ModelMapper modelMapper = new ModelMapper();

    @Test
    @DisplayName("convert using constructor when convert dto to entity")
    void convertUsingConstructorWhenConvertDtoToEntity() {
        StudentDto studentDto = new StudentDto(1L, "Xiaoming", "Zhang", 21);
        Student student = new Student(studentDto);
        assertStudent(studentDto, student);
    }

    @Test
    @DisplayName("convert using constructor when convert entity to dto")
    void convertUsingConstructorWhenConvertEntityToDto() {
        Student student = new Student(2L, "Four", "Lee", 22);
        StudentDto studentDto = new StudentDto(student);
        assertStudent(studentDto, student);
    }

    @Test
    @DisplayName("convert using conversion method when convert dto to entity")
    void convertUsingConversionMethodWhenConvertDtoToEntity() {
        StudentDto studentDto = new StudentDto(1L, "Xiaoming", "Zhang", 21);
        Student student = studentDto.toEntity();
        assertStudent(studentDto, student);
    }

    @Test
    @DisplayName("convert using conversion method when convert entity to dto")
    void convertUsingConversionMethodWhenConvertEntityToDto() {
        Student student = new Student(2L, "Four", "Lee", 22);
        StudentDto studentDto = student.toDto();
        assertStudent(studentDto, student);
    }

    @Test
    @DisplayName("convert using dedicated convertor when convert dto to entity")
    void convertUsingDedicatedConvertorWhenConvertDtoToEntity() {
        StudentDto studentDto = new StudentDto(1L, "Xiaoming", "Zhang", 21);
        Student student = new StudentMapper().toEntity(studentDto);
        assertStudent(studentDto, student);
    }

    @Test
    @DisplayName("convert using dedicated convertor when convert entity to dto")
    void convertUsingDedicatedConvertorWhenConvertEntityToDto() {
        Student student = new Student(2L, "Four", "Lee", 22);
        StudentDto studentDto = new StudentMapper().toDto(student);
        assertStudent(studentDto, student);
    }

    @Test
    @DisplayName("convert using model mapper when convert dto to entity")
    void convertUsingModelMapperWhenConvertDtoToEntity() {
        StudentDto studentDto = new StudentDto(1L, "Xiaoming", "Zhang", 21);
        Student student = modelMapper.map(studentDto, Student.class);
        assertStudent(studentDto, student);
    }

    @Test
    @DisplayName("convert using model mapper when convert entity to dto")
    void convertUsingModelMapperWhenConvertEntityToDto() {
        Student student = new Student(2L, "Four", "Lee", 22);
        StudentDto studentDto = modelMapper.map(student, StudentDto.class);
        assertStudent(studentDto, student);
    }

    private void assertStudent(StudentDto studentDto, Student student) {
        assertThat(student)
                .extracting(Student::getId, Student::getFirstName, Student::getLastName, Student:: getAge)
                .containsExactly(studentDto.getId(), studentDto.getFirstName(), studentDto.getLastName(), studentDto.getAge());
    }

}