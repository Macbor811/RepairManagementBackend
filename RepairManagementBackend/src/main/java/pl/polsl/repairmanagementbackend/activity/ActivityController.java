package pl.polsl.repairmanagementbackend.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.polsl.repairmanagementbackend.FinalizationData;

import java.time.Instant;
import java.time.LocalDateTime;


@RepositoryRestController
public class ActivityController {

    private final ActivityRepository repository;

    @Autowired
    public ActivityController(ActivityRepository repository) {
        this.repository = repository;
    }


    @PutMapping("activity/{id}/finalize")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody FinalizationData data){
        var entityOptional = repository.findById(Integer.valueOf(id));

        if (entityOptional.isPresent()){
            var oldEntity = entityOptional.get();

            var status = ActivityStatus
                    .fromString(data.getStatus())
                    .orElseThrow(() -> new IllegalArgumentException("Can't convert string to ActivityStatus"));


            if (status.hasEnded() && oldEntity.getEndDate() == null){
                oldEntity.setEndDate(Instant.now());
            }
            oldEntity.setResult(data.getResult());
            oldEntity.setStatus(data.getStatus());

            repository.save(oldEntity);

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
