package pl.polsl.repairmanagementbackend.activity;

import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler(ActivityEntity.class)
public class ActivityEventHandler {

    @HandleBeforeCreate
    public void handleBeforeCreate(ActivityEntity entity){
        entity.setStatus(ActivityStatus.OPEN.toString());
    }

}
