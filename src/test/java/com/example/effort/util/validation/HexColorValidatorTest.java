package com.example.effort.util.validation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HexColorValidatorTest {
    private HexColorValidator validator = new HexColorValidator();

    @Test
    public void valid3DigitHexCodesPass() {
        String hexCode1 = "#abc";
        String hexCode2 = "#0f0";

        boolean result1 = validator.isValid(hexCode1, null);
        boolean result2 = validator.isValid(hexCode2, null);

        assertTrue(result1);
        assertTrue(result2);
    }

    @Test
    public void valid6DigitHexCodesPass() {
        String hexCode1 = "#abcdef";
        String hexCode2 = "#ff021c";

        boolean result1 = validator.isValid(hexCode1, null);
        boolean result2 = validator.isValid(hexCode2, null);

        assertTrue(result1);
        assertTrue(result2);
    }

    @Test
    public void invalid3DigitHexCodeDoesntPass() {
        String hexCode = "#0xa";

        boolean result = validator.isValid(hexCode, null);

        assertFalse(result);
    }

    @Test
    public void invalid6DigitHexCodeDoesntPass() {
        String hexCode = "#12345x";

        boolean result = validator.isValid(hexCode, null);

        assertFalse(result);
    }

    @Test
    public void invalidLengthHexCodesDontPass() {
        String hexCode1 = "#1234567";
        String hexCode2 = "#00";

        boolean result1 = validator.isValid(hexCode1, null);
        boolean result2 = validator.isValid(hexCode2, null);

        assertFalse(result1);
        assertFalse(result2);
    }

    @Test
    public void missingHashHexCodeDoesntPass() {
        String hexCode = "123456";

        boolean result = validator.isValid(hexCode, null);

        assertFalse(result);
    }

}
