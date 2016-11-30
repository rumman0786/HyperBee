package net.therap.hyperbee.web.validator;

import net.therap.hyperbee.domain.Hive;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author azim
 * @since 11/30/16
 */
@Component
public class HiveValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(Hive.class);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
