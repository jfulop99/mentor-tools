package com.training360.mentortools.registration;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRegistrationCommand {

    @NotNull
    @Schema(description = "Existing student id", example = "1")
    private Long studentId;

}
