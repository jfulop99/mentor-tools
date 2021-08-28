package com.training360.mentortools.registration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisteredStudentDto {

    private Long id;

    private StudentRegistrationDto student;

    private RegistrationStatus registrationStatus;

}
