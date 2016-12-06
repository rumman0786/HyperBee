package net.therap.hyperbee.web.validator;


import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.domain.enums.DisplayStatus;
import net.therap.hyperbee.service.UserService;
import net.therap.hyperbee.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author rayed
 * @since 11/15/16 11:40 AM
 */
@Component
public class LoginValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> loginValidationClass) {

        return User.class.isAssignableFrom(loginValidationClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if (errors.hasErrors()) {
            return ;
        }

        User user = (User) target;

        User retrievedUser = userService.findByUsername(user.getUsername());

        if (retrievedUser == null) {
            errors.reject("user.not.found");
        } else if (!retrievedUser.getUsername().equals(user.getUsername())) {
            errors.rejectValue("username", "username.wrong");
        } else if (!retrievedUser.getPassword().equals(Utils.hashMd5(user.getPassword()))) {
            errors.rejectValue("password", "password.wrong");
        } else if (retrievedUser.getDisplayStatus() == DisplayStatus.INACTIVE) {
            errors.reject("user.inactive");
        }
    }
}
