package pl.polsl.repairmanagementbackend.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.time.LocalDateTime;


@RepositoryRestController
public class ActivityController {

    private final ActivityRepository repository;

    @Autowired
    public ActivityController(ActivityRepository repository) {
        this.repository = repository;
    }


//    @PutMapping("activity/{id}")
//    public ResponseEntity<?> update(@PathVariable String id, ActivityEntity entity){
//        var entityOptional = repository.findById(Integer.valueOf(id));
//
//        if (entityOptional.isPresent()){
//            var oldEntity = entityOptional.get();
//
//            var status = ActivityStatus
//                    .fromString(entity.getStatus())
//                    .orElseThrow(() -> new IllegalArgumentException("Can't convert string to ActivityStatus"));
//
//
//            if (status.hasEnded() && oldEntity.getEndDate() == null){
//                entity.setEndDate(LocalDateTime.now());
//            }
//
//            repository.save()
//
//            return ResponseEntity.ok();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//
//    }
}
