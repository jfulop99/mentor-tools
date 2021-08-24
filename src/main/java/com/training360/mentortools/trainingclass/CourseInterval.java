package com.training360.mentortools.trainingclass;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseInterval {

    @Column(name = "start_date")
    @Schema(description = "Start date of course", example = "2021-02-15")
    private LocalDate startDate;

    @Column(name = "end_date")
    @Schema(description = "End date of course", example = "2021-03-15")
    private LocalDate endDate;


}
