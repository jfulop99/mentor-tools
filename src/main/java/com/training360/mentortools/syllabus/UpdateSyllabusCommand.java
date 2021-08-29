package com.training360.mentortools.syllabus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSyllabusCommand {

    @NotNull
    @NotBlank
    @Length(min = 3, max = 255)
    private String name;

}
