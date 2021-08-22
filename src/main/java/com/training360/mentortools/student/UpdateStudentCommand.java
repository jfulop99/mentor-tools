package com.training360.mentortools.student;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class UpdateStudentCommand {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    @Email
    private String email;

    private String githubId;

    private String details;

}
