package pl.polsl.repairmanagementbackend.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.polsl.repairmanagementbackend.activity.ActivityDTO;
import pl.polsl.repairmanagementbackend.activity.ActivityEntity;
import pl.polsl.repairmanagementbackend.request.RequestDTO;
import pl.polsl.repairmanagementbackend.request.RequestEntity;

import javax.persistence.NoResultException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    private final EmployeeRepository repository;
    private final PasswordEncoder encoder;
    private final EmployeeMapper mapper;

    @Autowired
    public EmployeeController(EmployeeRepository repository, PasswordEncoder encoder, EmployeeMapper mapper) {
        this.repository = repository;
        this.encoder = encoder;
        this.mapper = mapper;
    }

    @GetMapping
    public List<EmployeeReadable> findAll(){

        List<EmployeeEntity> entities = repository.findAll();
        List<EmployeeReadable> dtos = entities.stream().map(mapper::toSent).collect(Collectors.toList());
        return dtos;
    }

    //@PreAuthorize("hasRole('MAN') || hasRole('ADM')")
    @PostMapping
    public ResponseEntity<?> save(@RequestBody EmployeeSaveable toSave) throws URISyntaxException {
        toSave.setPassword(encoder.encode(toSave.getPassword()));
        EmployeeEntity saved = repository.save(mapper.toEntity(toSave));
        return ResponseEntity.created(new URI("employee/" + saved.getId().toString())).build();


    }

    @PreAuthorize("hasRole('MAN') || (hasRole('WRK') && #id == principal.id)")
    @GetMapping("/{id}")
    public EmployeeReadable findById(@PathVariable int id){

        EmployeeEntity entity = repository.findById(id).orElseThrow(()-> new NoResultException("Entity not found"));

        return mapper.toSent(entity);
    }

//    @PreAuthorize("hasRole('MAN') || (hasRole('WRK') && #id == principal.id)")
//    @GetMapping("/{id}/activities")
//    public Collection<ActivityDTO> getActivities(@PathVariable int id){
//
//        EmployeeEntity entity = repository.findById(id).orElseThrow(()-> new NoResultException("Entity not found"));
//        return entity.getActivities().stream().map(ActivityEntity::toDTO).collect(Collectors.toList());
//
//    }

//    @PreAuthorize("hasRole('MAN')")
//    @GetMapping("/{id}/requests")
//    public Collection<RequestDTO> getRequests(@PathVariable int id){
//        EmployeeEntity entity = repository.findById(id).orElseThrow(()-> new NoResultException("Entity not found"));
//
//        return entity.getRequests().stream().map(RequestEntity::toDTO).collect(Collectors.toList());
//
//    }


    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<?> handleNoResultException(NoResultException e) {
        return ResponseEntity.notFound().build();
    }
}
