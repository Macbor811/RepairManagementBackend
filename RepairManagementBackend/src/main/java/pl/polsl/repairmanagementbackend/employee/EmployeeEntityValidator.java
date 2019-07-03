package pl.polsl.repairmanagementbackend.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component("beforeCreateEmployeeEntityValidator")
public class EmployeeEntityValidator implements Validator {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public boolean supports(Class<?> clazz) {
        return EmployeeEntity.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        var entity = (EmployeeEntity) target;

        var stringFields = Stream.of(EmployeeEntity.class.getDeclaredFields()).filter(field -> field.getType().equals(String.class)).collect(Collectors.toList());
        for (var field : stringFields){
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, field.getName(), field.getName() + ".emptyOrWhitespace","Empty or whitespace.");
        }

        if (errors.getFieldErrorCount("username") == 0){
            if (repository.findByUsername(entity.getUsername()).isPresent()) {
                errors.rejectValue("username", "username.exists", "This username is already in use.");
            } else if (!entity.getUsername().matches("[A-Za-z0-9_]+")){
                errors.rejectValue("username", "username.invalid", "Username can contain only latin letters, digits and underscore.");
            }
        }

        if (errors.getFieldErrorCount("role") == 0){
            if (Stream.of(EmployeeUserRole.values()).noneMatch(role -> role.toString().equals(entity.getRole()))) {
                errors.rejectValue("role", "role.invalid", "Invalid role.");
            }
        }



    }
}

