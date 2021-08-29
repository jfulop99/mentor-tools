package com.training360.mentortools.trainingclass;

import com.training360.mentortools.syllabus.SyllabusDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingClassDto {

    private Long id;

    private String name;

    private CourseInterval courseInterval;

    private SyllabusDto syllabus;
}
