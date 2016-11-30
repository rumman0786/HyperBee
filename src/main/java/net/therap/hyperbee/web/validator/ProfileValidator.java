package net.therap.hyperbee.web.validator;

import net.therap.hyperbee.domain.Profile;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author duity
 * @since 11/30/16.
 */
@Component
public class ProfileValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(Profile.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "designation", "profile.designation.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "joiningDate", "profile.joiningDate.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "profile.gender.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contactNo", "profile.contactNo.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "imagePath", "profile.imagePath.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "coverImage", "profile.coverImage.required");
    }
}
