package com.training360.mentortools.trainingclass;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class EndAfterStartValidator implements ConstraintValidator<EndAfterStart, CourseInterval> {
    @Override
    public boolean isValid(CourseInterval courseInterval, ConstraintValidatorContext constraintValidatorContext) {
        if (courseInterval == null){
            return true;
        }
        LocalDate startDate = courseInterval.getStartDate();
        LocalDate endDate = courseInterval.getEndDate();
        if (startDate == null){
            return endDate == null;
        }
        return endDate == null || endDate.isAfter(startDate);
    }
}
