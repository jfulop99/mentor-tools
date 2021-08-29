package com.training360.mentortools.student;

import com.training360.mentortools.registration.RecordNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.net.URI;
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

    public Student findStudentById(Long id){
        return studentRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(URI.create("student/not-found"), "Cannot find student id = " + id));
    }

    public StudentDto updateStudent(Long id, UpdateStudentCommand command) {
        Student student = findStudentById(id);
        student.setName(command.getName());
        student.setEmail(command.getEmail());
        student.setGithubId(command.getGithubId());
        student.setDetails(command.getDetails());
        studentRepository.save(student);
        return modelMapper.map(student, StudentDto.class);
    }

    public void deleteStudentById(Long id){
        Student student = findStudentById(id);
        studentRepository.delete(student);
    }
}
