package pl.polsl.repairmanagementbackend.springsocial.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import pl.polsl.repairmanagementbackend.employee.EmployeeRepository;
import pl.polsl.repairmanagementbackend.employee.EmployeeUser;
import pl.polsl.repairmanagementbackend.springsocial.exception.ResourceNotFoundException;
import pl.polsl.repairmanagementbackend.springsocial.model.SocialUserEntity;
import pl.polsl.repairmanagementbackend.springsocial.repository.SocialUserRepository;
import pl.polsl.repairmanagementbackend.springsocial.security.CurrentUser;
import pl.polsl.repairmanagementbackend.springsocial.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashSet;

@RestController
public class UserController {

    private class UserData{
        public String id;
        public String role;
        public String usernameOrEmail;

        public UserData(String id, String role, String usernameOrEmail) {
            this.id = id;
            this.role = role;
            this.usernameOrEmail = usernameOrEmail;
        }


    }

    @Autowired
    private SocialUserRepository socialUserRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/user/me")
    //@PreAuthorize("hasRole('SOCIAL_USER')")
    public UserData getCurrentUser(Principal userPrincipal) {

        var usernameOrEmail = userPrincipal.getName();

        var socialUser = socialUserRepository.findByEmail(usernameOrEmail);
        if (socialUser.isPresent()){
            return new UserData(socialUser.get().getId().toString(),
                    "SOCIAL_USER",
                    socialUser.get().getEmail()
            );
        } else {
            var employee = employeeRepository.findByUsername(usernameOrEmail);
            if (employee.isPresent()){

                return new UserData(
                        employee.get().getId().toString(),
                        employee.get().getRole(),
                        employee.get().getUsername()
                );
            }
        }

        throw new ResourceNotFoundException("SocialUserEntity", "id", userPrincipal.getName());
    }
}
