package pl.polsl.repairmanagementbackend.springsocial.security;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import pl.polsl.repairmanagementbackend.employee.EmployeeRepository;
import pl.polsl.repairmanagementbackend.employee.EmployeeUser;
import pl.polsl.repairmanagementbackend.springsocial.exception.ResourceNotFoundException;
import pl.polsl.repairmanagementbackend.springsocial.model.SocialUserEntity;
import pl.polsl.repairmanagementbackend.springsocial.repository.SocialUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

/**
 * Created by rajeevkumarsingh on 02/08/17.
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    SocialUserRepository socialUserRepository;

    @Autowired
    EmployeeRepository employeeRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        var socialUser = socialUserRepository.findByEmail(username);
        if (socialUser.isPresent()){
            return UserPrincipal.create(socialUser.get());
        } else {
            var employee = employeeRepository.findByUsername(username);
            if (employee.isPresent()){
                 var grantedAuthorities = new HashSet<GrantedAuthority>();
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + employee.get().getRole()));
                return new EmployeeUser(
                        employee.get().getUsername(),
                        employee.get().getPassword(),
                        grantedAuthorities,
                        employee.get().getId()
                );
            }
        }

        throw new UsernameNotFoundException("Could not find user identified by: " + username);

    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        SocialUserEntity user = socialUserRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("SocialUserEntity", "id", id)
        );

        return UserPrincipal.create(user);
    }
}