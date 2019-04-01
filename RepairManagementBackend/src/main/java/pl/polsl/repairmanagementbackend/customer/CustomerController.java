package pl.polsl.repairmanagementbackend.customer;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.repairmanagementbackend.address.AddressDTO;

import javax.persistence.NoResultException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("customer")
public class CustomerController {

    private final CustomerRepository repository;

    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }


    @GetMapping
    public List<CustomerDTO> findAll(){

        List<CustomerEntity> entities = repository.findAll();
        List<CustomerDTO> dtos = entities.stream().map(entity -> entity.toDTO()).collect(Collectors.toList());
        return dtos;
    }



    @PostMapping
    public ResponseEntity<?> save(@RequestBody CustomerDTO toSave) throws URISyntaxException {

        Integer id = repository.save(toSave.toEntity());
        return ResponseEntity.created(new URI("customer/" + id.toString())).build();


    }

    @GetMapping("/{id}")
    public CustomerDTO findById(@PathVariable int id){

        CustomerEntity entity = repository.findById(id);
        return entity.toDTO();
    }



    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<?> handleNoResultException(NoResultException e) {
        return ResponseEntity.notFound().build();
    }


}
