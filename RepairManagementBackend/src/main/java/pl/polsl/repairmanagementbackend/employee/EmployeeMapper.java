package pl.polsl.repairmanagementbackend.employee;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface EmployeeMapper {

    EmployeeEntity toEntity(EmployeeSaveable Employee);

    EmployeeReadable toSent(EmployeeEntity Employee);
}