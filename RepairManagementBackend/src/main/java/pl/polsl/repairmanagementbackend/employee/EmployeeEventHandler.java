package pl.polsl.repairmanagementbackend.employee;

import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
        import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler(EmployeeEntity.class)
public class EmployeeEventHandler {

    private PasswordEncoder encoder = new BCryptPasswordEncoder();

    @HandleBeforeCreate
    public void handleBeforeCreate(EmployeeEntity entity){

        entity.setPassword(encoder.encode(entity.getPassword()));
    }



}