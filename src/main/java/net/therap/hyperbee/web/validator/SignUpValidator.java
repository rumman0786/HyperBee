package net.therap.hyperbee.web.validator;


import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.service.UserService;
import net.therap.hyperbee.web.helper.SignUpUserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author rayed
 * @since 11/27/16 10:22 AM
 */
@Component
public class SignUpValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> signUpValidationClass) {
        return SignUpUserHelper.class.isAssignableFrom(signUpValidationClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "firstName.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "lastName.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password1", "password.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password2", "verifyPassword.required");

        SignUpUserHelper signUpUserHelper = (SignUpUserHelper) target;

        if (!signUpUserHelper.getPassword1().equals(signUpUserHelper.getPassword2())) {
            errors.rejectValue("password1", "password.mismatch");
        }

        User user = userService.findByUsernameOrEmail(signUpUserHelper.getUsername(), signUpUserHelper.getEmail());

        if (user != null) {
            if (user.getUsername().equals(signUpUserHelper.getUsername())) {
                errors.rejectValue("username", "username.unique");
            }
            if (user.getEmail().equals(signUpUserHelper.getEmail())) {
                errors.rejectValue("email", "email.unique");
            }
        }
    }
}
