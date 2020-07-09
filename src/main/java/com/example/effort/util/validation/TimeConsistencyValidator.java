package com.example.effort.util.validation;

import com.example.effort.time.TimeEntry;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TimeConsistencyValidator implements ConstraintValidator<TimeConsistent, TimeEntry> {

    @Override
    public void initialize(TimeConsistent constraintAnnotation) {
    }

    @Override
    public boolean isValid(TimeEntry entry, ConstraintValidatorContext constraintValidatorContext) {
        if (entry.getStartTime() != null && entry.getEndTime() != null) {
            return entry.getEndTime() >= entry.getStartTime();
        }
        return false;
    }

}
