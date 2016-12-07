package net.therap.hyperbee.web.validator;

import net.therap.hyperbee.domain.Hive;
import net.therap.hyperbee.service.HiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author azim
 * @since 11/30/16
 */
@Component
public class HiveValidator implements Validator {

    @Autowired
    private HiveService hiveService;

    @Override
    public boolean supports(Class clazz) {
        return Hive.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "hive.name.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "hive.description.required");

        if (hiveService.alreadyExist((Hive) target)) {
            errors.rejectValue("name", "hive.name.new");
        }
    }
}
