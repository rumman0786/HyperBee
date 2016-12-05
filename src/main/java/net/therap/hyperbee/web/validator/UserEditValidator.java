package net.therap.hyperbee.web.validator;

import net.therap.hyperbee.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author rayed
 * @since 12/5/16 3:31 PM
 */
@Component
public class UserEditValidator implements Validator {

    @Override
    public boolean supports(Class<?> userClass) {
        return User.class.isAssignableFrom(userClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "user.edit.password");
    }
}
