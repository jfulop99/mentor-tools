package com.training360.mentortools.trainingclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTrainingClassCommand {

    @NotNull
    @NotBlank
    @Length(max = 255)
    private String name;

    @EndAfterStart
    private CourseInterval courseInterval;

    public UpdateTrainingClassCommand(String name) {
        this.name = name;
    }
}
