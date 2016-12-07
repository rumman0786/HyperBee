package net.therap.hyperbee.web.validator;

import net.therap.hyperbee.domain.Profile;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author duity
 * @since 12/1/16.
 */
@Component
public class ProfileValidator implements Validator {
    @Override
    public boolean supports(Class clazz) {
        return Profile.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "designation", "profile.designation.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "profile.gender.required");
    }
}
