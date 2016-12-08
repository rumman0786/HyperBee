package net.therap.hyperbee.web.validator;

import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author rayed
 * @since 12/5/16 3:31 PM
 */
@Component
public class UserEditValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> userClass) {
        return User.class.isAssignableFrom(userClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        User retrievedUser = userService.findByUsername(user.getUsername());

        if (retrievedUser != null && (retrievedUser.getId() != user.getId())) {
            if (retrievedUser.getUsername().equals(user.getUsername())) {
                errors.rejectValue("username", "username.unique");
            }
        }

        retrievedUser = userService.findByEmail(user.getEmail());

        if (retrievedUser != null && (retrievedUser.getId() != user.getId())){
            if (retrievedUser.getEmail().equals(user.getEmail())) {
                errors.rejectValue("email", "email.unique");
            }
        }
    }
}
