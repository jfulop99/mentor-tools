package com.training360.mentortools.registration;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@Tag(name = "Operations on registration")
public class RegistrationController {

    private RegistrationService registrationService;

    @GetMapping("/trainingclasses/{id}/registrations")
    @Operation(summary = "List os students of class")
    public List<RegisteredStudentDto> getStudentRegistrations(@PathVariable Long id){
        return registrationService.getStudentRegistrations(id);
    }

    @GetMapping("/students/{id}/registrations")
    @Operation(summary = "List of classes of student")
    public List<RegisteredClassDto> getClassRegistration(@PathVariable Long id){
        return registrationService.getClassRegistration(id);
    }

    @PostMapping("/trainingclasses/{id}/registrations")
    @Operation(summary = "Create a registration")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisteredStudentDto createRegistrationWithStudent(@PathVariable Long id, @RequestBody CreateRegistrationCommand command){
        return registrationService.createRegistrationWithStudent(id, command);
    }

    @PutMapping("/trainingclasses/{id}/registrations")
    @Operation(summary = "Update a registration")
    public RegisteredStudentDto updateRegistration(@PathVariable Long id, @RequestBody UpdateRegistrationCommand command){
        return registrationService.updateRegistration(id, command);
    }

}
