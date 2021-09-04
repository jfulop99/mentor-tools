package com.training360.mentortools.lesson;

import com.training360.mentortools.module.ModuleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LessonDto {
    private Long id;

    private String title;

    private String url;

    private ModuleDto module;
}
