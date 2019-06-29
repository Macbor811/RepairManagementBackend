package pl.polsl.repairmanagementbackend.request;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pl.polsl.repairmanagementbackend.employee.EmployeeUserRole;

import java.util.stream.Stream;

@Component("beforeCreateRequestEntityValidator")
public class RequestEntityValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return RequestEntity.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        var entity = (RequestEntity) target;

        if (entity.getManager().roleEnum() != EmployeeUserRole.MANAGER){
            errors.rejectValue("manager.role", "manager.roleInvalid", "Assigned employee is not a manager.");
        }

        if (errors.getFieldErrorCount("status") == 0){
            if (Stream.of(RequestStatus.values()).noneMatch(role -> role.toString().equals(entity.getStatus()))) {
                errors.rejectValue("status", "status.invalid", "Invalid status.");
            } else {
                if (RequestStatus.fromString(entity.getStatus()).get().hasEnded()){
                    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "result", "result.emptyOrWhitespace","Empty or whitespace.");
                }
            }
        }
    }
}
