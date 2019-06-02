package pl.polsl.repairmanagementbackend.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class EmployeeUserDetailsService implements UserDetailsService {


    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeUserDetailsService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = employeeRepository.findByUsername(username);
        if (user.isPresent()){

            var grantedAuthorities = new HashSet<GrantedAuthority>();
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + user.get().getRole()));
            var employeeUser = new EmployeeUser(
                    user.get().getUsername(),
                    "{noop}" + user.get().getPassword(),
                    grantedAuthorities,
                    user.get().getId()
            );
            return employeeUser;
        } else {
            throw new UsernameNotFoundException("No employee with username " + username + " found.");
        }

    }
}
