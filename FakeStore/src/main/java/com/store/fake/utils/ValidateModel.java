package com.store.fake.utils;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public class ValidateModel {
    public <T> ResponseEntity<GeneralResponse> validateModel(T object)
    {
        String errors = "";
        ResponseEntity<GeneralResponse> response = null;
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<T>> violations = validator.validate(object);

        int last = 0;
        for (ConstraintViolation<T> violation : violations) {
            errors = violation.getMessage();
            last++;
            if(last == 1) {
                break;
            }
        }

        if(!errors.equals("")) {
            response = Autorizations.getResponsenBadRequest(null, errors);
            return response;
        }

        return null;
    }
}
