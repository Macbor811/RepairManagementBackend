package pl.polsl.repairmanagementbackend.activity;

import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;

@Component
@RepositoryEventHandler(ActivityEntity.class)
public class ActivityEventHandler {

    @HandleBeforeCreate
    public void handleBeforeCreate(ActivityEntity entity){

        entity.setStatus(ActivityStatus.OPEN.toString());
        entity.setResult(null);
    }


    @HandleBeforeSave
    public void handleBeforeSave(ActivityEntity entity){

        var status = ActivityStatus
                .fromString(entity.getStatus())
                .orElseThrow(() -> new IllegalArgumentException("Can't convert string to ActivityStatus"));


        if (status.hasEnded()){
            entity.setEndDate(Instant.now());
        }

    }

}
