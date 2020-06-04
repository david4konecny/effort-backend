package com.example.effort.util.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HexColorValidator implements ConstraintValidator<HexColor, String> {
    private static final String regexRule = "^#([0-9a-fA-F]{3}){1,2}$";
    private static final Pattern pattern = Pattern.compile(regexRule);

    @Override
    public void initialize(HexColor constraintAnnotation) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s != null) {
            Matcher matcher = pattern.matcher(s);
            return matcher.matches();
        }
        return false;
    }

}
