package pl.polsl.repairmanagementbackend.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import pl.polsl.repairmanagementbackend.activitytype.ActivityTypeRepository;
import pl.polsl.repairmanagementbackend.employee.EmployeeRepository;
import pl.polsl.repairmanagementbackend.request.RequestRepository;
import pl.polsl.repairmanagementbackend.request.RequestStatus;

import java.time.Instant;


@RepositoryRestController
public class ActivityController {

    private final ActivityRepository activityRepository;
    private final RequestRepository requestRepository;
    private final ActivityTypeRepository activityTypeRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public ActivityController(ActivityRepository activityRepository,
                              ActivityTypeRepository activityTypeRepository,
                              EmployeeRepository employeeRepository,
                              RequestRepository requestRepository) {
        this.activityRepository = activityRepository;
        this.activityTypeRepository = activityTypeRepository;
        this.employeeRepository = employeeRepository;
        this.requestRepository = requestRepository;
    }


    @PutMapping("activity/{id}/update")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody ActivityUpdateDto data){
        var entityOptional = activityRepository.findById(Integer.valueOf(id));

        if (entityOptional.isPresent()){
            var oldEntity = entityOptional.get();

            var status = ActivityStatus
                    .fromString(data.getStatus())
                    .orElseThrow(() -> new IllegalArgumentException("Can't convert string to ActivityStatus"));


            if (status.equals(ActivityStatus.IN_PROGRESS)){
                var request = oldEntity.getRequest();
                request.setStatus(RequestStatus.IN_PROGRESS.toString());
                requestRepository.save(request);
            } else if (status.hasEnded() && oldEntity.getEndDate() == null){
                oldEntity.setEndDate(Instant.now());
            }
            oldEntity.setResult(data.getResult());
            oldEntity.setStatus(data.getStatus());
            oldEntity.setDescription(data.getDescription());
            oldEntity.setSequenceNum(data.getSequenceNum());

            var employee = data.getWorkerId() != null ? employeeRepository.findById(data.getWorkerId()).get() : null;
            var type = activityTypeRepository
                    .findByType(data.getType())
                    .orElseThrow(() -> new IllegalArgumentException("Can't find this activity type."));

            oldEntity.setWorker(employee);
            oldEntity.setActivityType(type);

            activityRepository.save(oldEntity);

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("activity/{id}/reorder")
    public ResponseEntity<?> update(@PathVariable String id, @RequestParam Integer delta){
        var entityOptional = activityRepository.findById(Integer.valueOf(id));

        if (entityOptional.isPresent()){
            var oldEntity = entityOptional.get();

            oldEntity.setSequenceNum(oldEntity.getSequenceNum() + delta);

            activityRepository.save(oldEntity);

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
