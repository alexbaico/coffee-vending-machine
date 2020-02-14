package bayer.test.coffeevendingmachine.dto;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

public class BaseResponseDTO {

    private Map<String, String> errors = new HashMap<>();

    public BaseResponseDTO() {
    }

    public BaseResponseDTO(BindingResult bindingResult) {
        addErrors(bindingResult);
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void addError(String name, String message) {
        errors.put(name, message);
    }

    public void addErrors(BindingResult bindingResult) {
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }

    public void addErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
