package pl.polsl.repairmanagementbackend.customer;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component("beforeCreateCustomerEntityValidator")
public class CustomerEntityValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return CustomerEntity.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        var entity = (CustomerEntity) target;

        var stringFields = Stream.of(CustomerEntity.class.getDeclaredFields()).filter(field -> field.getType().equals(String.class)).collect(Collectors.toList());
        for (var field : stringFields){
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, field.getName(), field.getName() + ".emptyOrWhitespace","Empty or whitespace.");
        }

        if (errors.getErrorCount() == stringFields.size()) {
            return;
        }

//        if (errors.getFieldErrorCount("firstName") == 0){
//            if (!StringUtils.isAlpha(entity.getLastName())){
//                errors.rejectValue("firstName", "firstName.invalidChars", "Contains illegal characters");
//            }
//        }
//        if (errors.getFieldErrorCount("lastName") == 0){
//
//        }
//        if (errors.getFieldErrorCount("phoneNumber") == 0){
//            if (entity.getPhoneNumber().length() < 9){
//                errors.rejectValue("phoneNumber", "phoneNumber.tooShort","Invalid phone number.");
//            }
//        }
    }
}
