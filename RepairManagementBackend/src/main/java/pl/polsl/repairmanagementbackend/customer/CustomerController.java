package pl.polsl.repairmanagementbackend.customer;

import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CustomerController {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("api/customer/search")
    public List<String> findByFullName(@RequestParam String fullName){
        String[] words = fullName.split("\\s+");
        Pageable firstTen = new PageRequest(0, 10);
        List<CustomerEntity> list;
        if (words.length == 0 || words[0].isEmpty()){
            list = customerRepository
                    .findAll(firstTen)
                    .getContent();
        } else if (words.length == 1){
            list = customerRepository
                    .findByFirstNameStartsWithIgnoreCaseOrLastNameStartsWithIgnoreCase(words[0], words[0], firstTen)
                    .getContent();
        } else {
            list = customerRepository
                    .findByFirstNameStartsWithIgnoreCaseAndLastNameStartsWithIgnoreCase(words[0], words[1], firstTen)
                    .getContent();
        }
        return  list
                .stream()
                .map(e -> e.getFirstName() + " " + e.getLastName() + "; " + e.getId())
                .collect(Collectors.toList());
    }
 }
