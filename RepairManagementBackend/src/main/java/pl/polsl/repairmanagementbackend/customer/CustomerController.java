package pl.polsl.repairmanagementbackend.customer;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("customer")
public class CustomerController {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public CustomerController(CustomerRepository repository, CustomerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Secured({"ROLE_MAN", "ROLE_ADM"})
    @GetMapping
    public List<CustomerReadable> findAll(){

        List<CustomerEntity> entities = repository.findAll();
        List<CustomerReadable> dtos = entities.stream().map(mapper::toSent).collect(Collectors.toList());
        return dtos;
    }


    @PreAuthorize("hasRole('ADM')")//|| hasAnyAuthority('ADM')")
    @PostMapping
    public ResponseEntity<?> save(@RequestBody CustomerSaveable toSave) throws URISyntaxException {

        CustomerEntity saved = repository.save(mapper.toEntity(toSave));
        return ResponseEntity.created(new URI("customer/" + saved.getId().toString())).build();


    }

    @PreAuthorize("hasRole('ADM') && #id == principal.id")
    @GetMapping("/{id}")
    public CustomerReadable findById(@PathVariable int id){

        Optional<CustomerEntity> entity = repository.findById(id);
        if (entity.isPresent())
            return mapper.toSent(entity.get());
        else
            throw new NoResultException("Entity not found");
    }



}
