package pl.polsl.repairmanagementbackend.customer;


import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring")
@Component
public interface CustomerMapper {

    CustomerEntity toEntity(CustomerSaveable customer);

    CustomerReadable toSent(CustomerEntity customer);
}
