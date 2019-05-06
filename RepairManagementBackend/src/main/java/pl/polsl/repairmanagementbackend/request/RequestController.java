package pl.polsl.repairmanagementbackend.request;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.repairmanagementbackend.address.AddressEntity;
import pl.polsl.repairmanagementbackend.customer.CustomerEntity;
import pl.polsl.repairmanagementbackend.customer.CustomerRepository;
import pl.polsl.repairmanagementbackend.employee.EmployeeEntity;
import pl.polsl.repairmanagementbackend.employee.EmployeeRepository;
import pl.polsl.repairmanagementbackend.item.ItemEntity;
import pl.polsl.repairmanagementbackend.item.ItemRepository;
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
    private final EmployeeRepository employeeRepository;
    private final ItemRepository itemRepository;

    private final RequestMapper requestMapper;

    @Autowired
    public RequestController(RequestRepository repository, CustomerRepository customerRepository, EmployeeRepository employeeRepository, ItemRepository itemRepository, RequestMapper requestMapper) {
        this.repository = repository;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.itemRepository = itemRepository;
        this.requestMapper = requestMapper;
    }


    @GetMapping
    public List<RequestReadable> findAll(){

        List<RequestEntity> entities = repository.findAll();
        List<RequestReadable> dtos = entities.stream().map(requestMapper::toSent).collect(Collectors.toList());
        return dtos;
    }


    @PostMapping("/postTest")
    public ResponseEntity<?> save() throws URISyntaxException {

        CustomerEntity customer = customerRepository.findById(1).get();

        ItemTypeEntity type = new ItemTypeEntity();
        type.setType("typ");

        EmployeeEntity employee = employeeRepository.findById(1).get();

        employee.setAddress(new AddressEntity());

        RequestEntity request = new RequestEntity("opis", "wyn", "sta",
                new Timestamp(System.currentTimeMillis()),  new Timestamp(System.currentTimeMillis()), null,
                new ItemEntity("item", type, customer), employee);


        Integer id = repository.save(request).getId();
        return ResponseEntity.created(new URI("request/" + id.toString())).build();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody RequestSaveable toSave) throws URISyntaxException {
        ItemEntity item = itemRepository
                .findById(toSave.getItemId())
                .orElseThrow(() -> new NoResultException("No item with ID: " + toSave.getItemId()));

        EmployeeEntity manager = employeeRepository
                .findById(toSave.getManagerId())
                .orElseThrow(() -> new NoResultException("No employee with ID: " + toSave.getManagerId()));


        RequestEntity entity = requestMapper.toEntity(toSave);
        entity.setManager(manager);
        entity.setItem(item);

        Integer id = repository.save(entity).getId();
        return ResponseEntity.created(new URI("request/" + id.toString())).build();
    }

    @GetMapping("/{id}")
    public RequestReadable findById(@PathVariable int id){

        RequestEntity entity = repository.findById(id);
        return requestMapper.toSent(entity);
    }



    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<?> handleNoResultException(NoResultException e) {
        return ResponseEntity.notFound().build();
    }
}
