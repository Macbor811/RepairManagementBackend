package pl.polsl.repairmanagementbackend.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Instant;


@RepositoryRestController
public class RequestController {

    private final RequestRepository repository;

    @Autowired
    public RequestController(RequestRepository repository) {
        this.repository = repository;
    }


    @PutMapping("request/{id}/update")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody RequestUpdateDto data){
        var entityOptional = repository.findById(Integer.valueOf(id));

        if (entityOptional.isPresent()){
            var oldEntity = entityOptional.get();

            var status = RequestStatus
                    .fromString(data.getStatus())
                    .orElseThrow(() -> new IllegalArgumentException("Can't convert string to RequestStatus"));

            if (status.hasEnded() && oldEntity.getEndDate() == null){
                oldEntity.setEndDate(Instant.now());
            }
            oldEntity.setDescription(data.getDescription());
            oldEntity.setResult(data.getResult());
            oldEntity.setStatus(data.getStatus());

            repository.save(oldEntity);

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
