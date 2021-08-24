package com.training360.mentortools.trainingclass;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Data
public class CreateTrainingClassCommand {

    @NotNull
    @NotBlank
    @Length(max = 255)
    @Schema(description = "Name of trainingclass", example = "First Training")
    private String name;

    @EndAfterStart
    private CourseInterval courseInterval;

    public CreateTrainingClassCommand(String name) {
        this.name = name;
    }
}
