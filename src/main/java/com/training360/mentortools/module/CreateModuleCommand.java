package com.training360.mentortools.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateModuleCommand {

    @NotNull
    @Length(min = 3, max = 255)
    private String title;

    @NotNull
    @Length(min = 3, max = 255)
    private String url;

}
