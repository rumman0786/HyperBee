package net.therap.hyperbee.web.validator;


import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.service.UserService;
import net.therap.hyperbee.web.command.SignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
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
        return SignUpDto.class.isAssignableFrom(signUpValidationClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

        SignUpDto signUpDto = (SignUpDto) target;

        if (!signUpDto.getPassword1().equals(signUpDto.getPassword2())) {
            errors.rejectValue("password1", "password.mismatch");
        }

        User user = userService.findByUsername(signUpDto.getUsername());

        if (user != null) {
            errors.rejectValue("username", "username.wrong");
        }
    }
}
