package pl.polsl.repairmanagementbackend.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.repairmanagementbackend.address.AddressEntity;
import pl.polsl.repairmanagementbackend.customer.CustomerEntity;
import pl.polsl.repairmanagementbackend.customer.CustomerRepository;
import pl.polsl.repairmanagementbackend.employee.EmployeeEntity;
import pl.polsl.repairmanagementbackend.item.ItemEntity;
import pl.polsl.repairmanagementbackend.itemtype.ItemTypeEntity;

import javax.persistence.NoResultException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("request")
public class RequestController {

    private final RequestRepository repository;

    private final CustomerRepository customerRepository;

    @Autowired
    public RequestController(RequestRepository repository, CustomerRepository customerRepository) {
        this.repository = repository;
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public List<RequestDTO> findAll(){


        CustomerEntity customer = customerRepository.findById(1);

        ItemTypeEntity type = new ItemTypeEntity();
        type.setType("typ");

        EmployeeEntity employee = new EmployeeEntity();

        employee.setAddress(new AddressEntity());

        RequestEntity request = new RequestEntity("opis", "wyn", "sta",
                new Timestamp(System.currentTimeMillis()),  new Timestamp(System.currentTimeMillis()), null,
                new ItemEntity("a", type, customer), employee);

       repository.save(request);

        List<RequestEntity> entities = repository.findAll();
        List<RequestDTO> dtos = entities.stream().map(RequestEntity::toDTO).collect(Collectors.toList());
        return dtos;
    }



    @PostMapping
    public ResponseEntity<?> save(@RequestBody RequestDTO toSave) throws URISyntaxException {

        Integer id = repository.save(toSave.toEntity());
        return ResponseEntity.created(new URI("request/" + id.toString())).build();


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
