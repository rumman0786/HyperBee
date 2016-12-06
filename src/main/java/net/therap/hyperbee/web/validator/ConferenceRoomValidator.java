package net.therap.hyperbee.web.validator;

import net.therap.hyperbee.domain.ConferenceRoom;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author rumman
 * @since 11/28/16
 */
@Component
public class ConferenceRoomValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ConferenceRoom.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "conference.title.required");

        ConferenceRoom conferenceRoom = (ConferenceRoom) target;

        try {
            if (conferenceRoom.getCapacity() < 1) {
                errors.rejectValue("capacity", "conference.capacity.required");
            }
        } catch (NumberFormatException e) {
            errors.rejectValue("capacity", "conference.capacity.required");
        }
    }
}
