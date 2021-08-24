package com.training360.mentortools.student;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@AllArgsConstructor
@Tag(name = "Operations on students")
public class StudentController {

    private StudentService studentService;

    @GetMapping
    @Operation(description = "List all students")
    public List<StudentDto> getStudents(){
        return studentService.getStudents();
    }

    @PostMapping
    @Operation(description = "Create a student")
    public StudentDto createStudent(@Valid @RequestBody CreateStudentCommand command){
        return studentService.createStudent(command);
    }

    @GetMapping("/{id}")
    @Operation(description = "Get student by id")
    public StudentDto getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);
    }

    @PutMapping("/{id}")
    @Operation(description = "Update a student by id")
    public StudentDto updateStudentById(@PathVariable Long id, @RequestBody UpdateStudentCommand command){
        return studentService.updateStudent(id, command);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Delete a student by id")
    public void deleteStudentById(@PathVariable Long id){
        studentService.deleteStudentById(id);
    }
}
