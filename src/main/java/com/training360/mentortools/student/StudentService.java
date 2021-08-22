package com.training360.mentortools.student;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {

    private StudentRepository studentRepository;

    private ModelMapper modelMapper;

    public List<StudentDto> getStudents() {
        return studentRepository.findAll()
                .stream()
                .map(student -> modelMapper.map(student, StudentDto.class))
                .toList();
    }

    public StudentDto createStudent(CreateStudentCommand command) {

        Student student = new Student(command.getName(), command.getEmail(),
                command.getGithubId(), command.getDetails());

        studentRepository.save(student);

        return modelMapper.map(student, StudentDto.class);
    }

    public StudentDto getStudentById(Long id) {
        return modelMapper.map(findStudentById(id), StudentDto.class);
    }

    private Student findStudentById(Long id){
        return studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cannot find student id = " + id));
    }
}