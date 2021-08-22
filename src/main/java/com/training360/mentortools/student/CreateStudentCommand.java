package com.training360.mentortools.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateStudentCommand {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    @Email
    private String email;

    private String githubId;

    private String details;

    public CreateStudentCommand(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
