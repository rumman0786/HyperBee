package net.therap.hyperbee.web.validator;


import net.therap.hyperbee.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author rayed
 * @since 11/15/16 11:40 AM
 */
@Component
public class LoginValidator implements Validator {

    @Override
    public boolean supports(Class<?> loginValidationClass) {
        return User.class.isAssignableFrom(loginValidationClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        System.out.println("here at login validation");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required");
    }
}
