package com.training360.mentortools.syllabus;

import com.training360.mentortools.module.ModuleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SyllabusDto {

    private Long id;

    private String name;

    private List<ModuleDto> modules;
}
