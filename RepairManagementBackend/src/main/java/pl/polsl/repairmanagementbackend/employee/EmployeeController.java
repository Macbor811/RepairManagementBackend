package pl.polsl.repairmanagementbackend.employee;

import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("api/employee/search")
    public List<String> findByFullName(@RequestParam String fullName, @RequestParam String role){
        String[] words = fullName.split("\\s+");
        Pageable firstTen = new PageRequest(0, 10);
        List<EmployeeEntity> list;
        if (words.length == 0 || words[0].isEmpty()){
            list = employeeRepository
                    .findAll(firstTen)
                    .getContent();
        } else if (words.length == 1){
            list = employeeRepository
                    .findByFirstNameStartsWithIgnoreCaseOrLastNameStartsWithIgnoreCase(words[0], words[0], firstTen)
                    .getContent();
        } else {
            list = employeeRepository
                    .findByFirstNameStartsWithIgnoreCaseAndLastNameStartsWithIgnoreCase(words[0], words[1], firstTen)
                    .getContent();
        }
        return  list
                .stream()
                .filter(e -> e.getRole().equalsIgnoreCase(role))
                .map(e -> e.getFirstName() + " " + e.getLastName() + "; " + e.getId())
                .collect(Collectors.toList());
    }

    private  final PasswordEncoder encoder = new BCryptPasswordEncoder();

    @PutMapping("api/employee/{id}/update-user")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody EmployeeUserDataDto data){

        var entityOptional = employeeRepository.findById(Integer.valueOf(id));

        if (entityOptional.isPresent()){
            var oldEntity = entityOptional.get();

            if (data.getPassword() != null && data.getPassword().length() > 0)
                oldEntity.setPassword(encoder.encode(data.getPassword()));
            oldEntity.setDeactivationDate(data.getDeactivationDate());

            employeeRepository.save(oldEntity);

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
