package com.training360.mentortools.student;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "Name of student", example = "John Doe")
    private String name;

    @NotNull
    @NotBlank
    @Email
    @Schema(description = "E-mail address of student", example = "john.doe@domain.com")
    private String email;

    @Schema(description = "GitHub id of student", example = "github")
    private String githubId;

    @Schema(description = "Notes", example = "note")
    private String details;

}
