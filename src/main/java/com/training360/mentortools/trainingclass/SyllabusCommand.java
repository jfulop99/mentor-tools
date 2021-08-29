package com.training360.mentortools.trainingclass;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SyllabusCommand {

    @NotNull
    @Schema(description = "Id of syllabus")
    private Long syllabusId;
}
