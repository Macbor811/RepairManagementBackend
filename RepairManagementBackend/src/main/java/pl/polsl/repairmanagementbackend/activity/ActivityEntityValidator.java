package pl.polsl.repairmanagementbackend.activity;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component("beforeSaveActivityEntityValidator")
public class ActivityEntityValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
       return ActivityEntity.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        var entity = (ActivityEntity) target;

        if (errors.getFieldErrorCount("role") == 0){
            if (Stream.of(ActivityStatus.values()).noneMatch(role -> role.toString().equals(entity.getStatus()))) {
                errors.rejectValue("role", "role.invalid", "Invalid role.");
            } else {
                if (ActivityStatus.fromString(entity.getStatus()).get().hasEnded()){
                    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "result", "result.emptyOrWhitespace","Empty or whitespace.");
                }
            }
        }
    }
}
