package pl.polsl.repairmanagementbackend.itemtype;

import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;
import pl.polsl.repairmanagementbackend.activity.ActivityEntity;
import pl.polsl.repairmanagementbackend.activity.ActivityStatus;

import java.time.Instant;

@Component
@RepositoryEventHandler(ItemTypeEntity.class)
public class ItemTypeEventHandler {

    @HandleBeforeCreate
    public void handleBeforeCreate(ItemTypeEntity entity){

        entity.setId(entity.getType());
    }


}
