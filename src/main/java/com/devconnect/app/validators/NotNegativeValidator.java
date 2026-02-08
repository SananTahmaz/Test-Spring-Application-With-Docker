package com.devconnect.app.validators;

import com.devconnect.app.annotations.NotNegative;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;

public class NotNegativeValidator implements ConstraintValidator<NotNegative, Object> {
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        if (value instanceof Number) {
            BigDecimal number = toBigDecimal((Number) value);
            return number.compareTo(BigDecimal.ZERO) >= 0;
        }
        return false;
    }

    private BigDecimal toBigDecimal(Number number) {
        if (number instanceof BigDecimal) {
            return (BigDecimal) number;
        }
        if (number instanceof Long || number instanceof Integer || number instanceof Short || number instanceof Byte) {
            return BigDecimal.valueOf(number.longValue());
        }
        return BigDecimal.valueOf(number.doubleValue());
    }
}
