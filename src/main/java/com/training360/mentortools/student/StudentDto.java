package com.training360.mentortools.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    private Long id;

    private String name;

    private String email;

    private String githubId;

    private String details;

}
