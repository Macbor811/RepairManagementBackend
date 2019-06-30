package pl.polsl.repairmanagementbackend.springsocial.controller;

import pl.polsl.repairmanagementbackend.springsocial.exception.ResourceNotFoundException;
import pl.polsl.repairmanagementbackend.springsocial.model.SocialUserEntity;
import pl.polsl.repairmanagementbackend.springsocial.repository.SocialUserRepository;
import pl.polsl.repairmanagementbackend.springsocial.security.CurrentUser;
import pl.polsl.repairmanagementbackend.springsocial.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private SocialUserRepository socialUserRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('SOCIAL_USER')")
    public SocialUserEntity getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return socialUserRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("SocialUserEntity", "id", userPrincipal.getId()));
    }
}
