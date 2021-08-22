package com.training360.mentortools.student;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@AllArgsConstructor
public class StudentController {

    private StudentService studentService;

    @GetMapping
    public List<StudentDto> getStudents(){
        return studentService.getStudents();
    }

    @PostMapping
    public StudentDto createStudent(@Valid @RequestBody CreateStudentCommand command){
        return studentService.createStudent(command);
    }

    @GetMapping("/{id}")
    public StudentDto getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);
    }
}
