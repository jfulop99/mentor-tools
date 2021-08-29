package com.training360.mentortools.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModuleDto {

    private Long id;

    private String title;

    private String url;

}
