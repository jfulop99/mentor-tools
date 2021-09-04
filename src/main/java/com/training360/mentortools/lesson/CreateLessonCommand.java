package com.training360.mentortools.lesson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateLessonCommand {

    @NotNull
    @NotBlank
    private String title;

    @NotNull
    @NotBlank
    private String url;

}
