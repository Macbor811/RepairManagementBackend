package pl.polsl.repairmanagementbackend.springsocial.controller;

import pl.polsl.repairmanagementbackend.springsocial.exception.ResourceNotFoundException;
import pl.polsl.repairmanagementbackend.springsocial.model.UserEntity;
import pl.polsl.repairmanagementbackend.springsocial.repository.UserRepository;
import pl.polsl.repairmanagementbackend.springsocial.security.CurrentUser;
import pl.polsl.repairmanagementbackend.springsocial.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserEntity getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("UserEntity", "id", userPrincipal.getId()));
    }
}
