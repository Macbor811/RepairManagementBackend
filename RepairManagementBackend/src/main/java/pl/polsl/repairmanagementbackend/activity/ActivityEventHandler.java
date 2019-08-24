package pl.polsl.repairmanagementbackend.activity;

import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;
import pl.polsl.repairmanagementbackend.request.RequestRepository;
import pl.polsl.repairmanagementbackend.request.RequestStatus;

import java.time.Instant;

@Component
@RepositoryEventHandler(ActivityEntity.class)
public class ActivityEventHandler {

    private final ActivityRepository activityRepository;
    private final RequestRepository requestRepository;

    public ActivityEventHandler(ActivityRepository activityRepository, RequestRepository requestRepository) {
        this.activityRepository = activityRepository;
        this.requestRepository = requestRepository;
    }

    @HandleBeforeCreate
    public void handleBeforeCreate(ActivityEntity entity){

        var status = ActivityStatus
                .fromString(entity.getStatus())
                .orElseThrow(() -> new IllegalArgumentException("Can't convert string to ActivityStatus"));


        if (status.equals(ActivityStatus.IN_PROGRESS)){
            var request = entity.getRequest();
            request.setStatus(RequestStatus.IN_PROGRESS.toString());
            requestRepository.save(request);
        }

        entity.setSequenceNum(entity
                .getRequest()
                .getActivities()
                .stream()
                .map(ActivityEntity::getSequenceNum)
                .max(Integer::compareTo)
                .orElse(0) + 1
        );
        entity.setResult(null);
        entity.setRegisterDate(Instant.now());
    }


    @HandleBeforeSave
    public void handleBeforeSave(ActivityEntity entity){

        var status = ActivityStatus
                .fromString(entity.getStatus())
                .orElseThrow(() -> new IllegalArgumentException("Can't convert string to ActivityStatus"));


        if (status.equals(ActivityStatus.IN_PROGRESS)){
            var request = entity.getRequest();
            request.setStatus(RequestStatus.IN_PROGRESS.toString());
            requestRepository.save(request);
        }

        if (status.hasEnded()){
            entity.setEndDate(Instant.now());
        }


    }

}
