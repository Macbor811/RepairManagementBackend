package pl.polsl.repairmanagementbackend.employee;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class EmployeeUser extends User {

    private final int id;

    public EmployeeUser(String username,
                        String password,
                        Collection<? extends GrantedAuthority> authorities,
                        int id
    ) {
        super(username, password, authorities);
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

