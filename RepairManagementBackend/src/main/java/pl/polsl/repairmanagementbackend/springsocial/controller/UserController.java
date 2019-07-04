package pl.polsl.repairmanagementbackend.springsocial.controller;

import org.springframework.web.bind.annotation.*;
import pl.polsl.repairmanagementbackend.customer.CustomerRepository;
import pl.polsl.repairmanagementbackend.employee.EmployeeRepository;
import pl.polsl.repairmanagementbackend.springsocial.exception.ResourceNotFoundException;
import pl.polsl.repairmanagementbackend.springsocial.repository.SocialUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;
import java.time.Instant;

@RestController
public class UserController {

    private class UserData{
        public String id;
        public String role;
        public String usernameOrEmail;
        public Boolean active;

        public UserData(Boolean active, String id, String role, String usernameOrEmail) {
            this.active = active;
            this.id = id;
            this.role = role;
            this.usernameOrEmail = usernameOrEmail;
        }
    }

    @Autowired
    private SocialUserRepository socialUserRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/user/me")
    //@PreAuthorize("hasRole('SOCIAL_USER')")
    public UserData getCurrentUser(Principal userPrincipal) {

        var usernameOrEmail = userPrincipal.getName();

        var socialUser = socialUserRepository.findByEmail(usernameOrEmail);
        if (socialUser.isPresent()){
            Boolean isVerified = socialUser.get().getCustomer() != null;
            return new UserData(
                    isVerified,
                    isVerified ? socialUser.get().getCustomer().getId().toString() : null,
                    "SOCIAL_USER",
                    socialUser.get().getEmail()
            );
        } else {
            var employee = employeeRepository.findByUsername(usernameOrEmail);
            if (employee.isPresent()){
                Boolean isActive = employee.get().getDeactivationDate() == null || employee.get().getDeactivationDate().isAfter(Instant.now());
                return new UserData(
                        isActive,
                        employee.get().getId().toString(),
                        employee.get().getRole(),
                        employee.get().getUsername()
                );
            }
        }

        throw new ResourceNotFoundException("User", "usernameOrEmail", userPrincipal.getName());
    }


    @PostMapping("/user/me/{id}")
    public void assign(@PathVariable Integer id, Principal userPrincipal) {
        var customer = customerRepository.findById(id);

        var user = socialUserRepository.findByEmail(userPrincipal.getName());

        if (user.isPresent() && customer.isPresent()){
            user.get().setCustomer(customer.get());
            socialUserRepository.save(user.get());
        } else {
            throw  new IllegalArgumentException("Invalid user or customer");
        }
    }

    @PutMapping("/user/{userId}/{customerId}")
     public void assign(@PathVariable Long userId, @PathVariable Integer customerId){
        var customer = customerRepository.findById(customerId);

        var user = socialUserRepository.findById(userId);

        if (user.isPresent() && customer.isPresent()){
            user.get().setCustomer(customer.get());
            socialUserRepository.save(user.get());
        } else {
            throw  new IllegalArgumentException("Invalid user or customer");
        }
    }

}
