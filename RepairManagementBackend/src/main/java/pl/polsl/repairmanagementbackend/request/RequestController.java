package pl.polsl.repairmanagementbackend.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("request")
public class RequestController {

    private final RequestRepository repository;

    @Autowired
    public RequestController(RequestRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<RequestDTO> findAll(){

        List<RequestEntity> entities = repository.findAll();
        List<RequestDTO> dtos = entities.stream().map(RequestEntity::toDTO).collect(Collectors.toList());
        return dtos;
    }



    @PostMapping
    public ResponseEntity<?> save(@RequestBody RequestDTO toSave) throws URISyntaxException {

        Integer id = repository.save(toSave.toEntity());
        return ResponseEntity.created(new URI("customer/" + id.toString())).build();


    }

    @GetMapping("/{id}")
    public RequestDTO findById(@PathVariable int id){

        RequestEntity entity = repository.findById(id);
        return entity.toDTO();
    }



    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<?> handleNoResultException(NoResultException e) {
        return ResponseEntity.notFound().build();
    }
}
