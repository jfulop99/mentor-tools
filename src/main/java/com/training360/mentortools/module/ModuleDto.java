package com.training360.mentortools.module;

import com.training360.mentortools.lesson.Lesson;
import com.training360.mentortools.lesson.LessonDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModuleDto {

    private Long id;

    private String title;

    private String url;

    private List<LessonDto> lessons;

}
