package pl.polsl.repairmanagementbackend.request;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public abstract class RequestMapper {

    abstract RequestEntity toEntity(RequestSaveable request);


    @Mapping(source = "manager.id", target = "managerId")
    @Mapping(source = "item.id", target = "itemId")
    abstract RequestReadable toInternal(RequestEntity request);

    RequestReadable toSent(RequestEntity entity){
        RequestReadable readable = toInternal(entity);
        readable.setManagerName(entity.getManager().getFirstName() + " " + entity.getManager().getLastName());

        readable.setItemName(entity.getItem().getName());
        return readable;
    }
}
