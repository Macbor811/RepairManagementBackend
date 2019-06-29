package pl.polsl.repairmanagementbackend.request;

import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RepositoryEventHandler(RequestEntity.class)
public class RequestEventHandler {

    @HandleBeforeCreate
    public void handleBeforeCreate(RequestEntity entity){

        entity.setStatus(RequestStatus.OPEN.toString());
        entity.setResult(null);
        entity.setRegisterDate(Instant.now());
    }


    @HandleBeforeSave
    public void handleBeforeSave(RequestEntity entity){

        var status = RequestStatus
                .fromString(entity.getStatus())
                .orElseThrow(() -> new IllegalArgumentException("Can't convert string to RequestStatus"));


        if (status.hasEnded()){
            entity.setEndDate(Instant.now());
        }

    }

}
