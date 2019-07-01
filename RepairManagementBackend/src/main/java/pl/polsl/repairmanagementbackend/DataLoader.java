package pl.polsl.repairmanagementbackend;


        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.ApplicationArguments;
        import org.springframework.boot.ApplicationRunner;
        import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
        import org.springframework.security.core.Authentication;
        import org.springframework.security.core.GrantedAuthority;
        import org.springframework.security.core.authority.SimpleGrantedAuthority;
        import org.springframework.security.core.context.SecurityContextHolder;
        import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
        import org.springframework.stereotype.Component;
        import pl.polsl.repairmanagementbackend.address.AddressEntity;
        import pl.polsl.repairmanagementbackend.employee.EmployeeEntity;
        import pl.polsl.repairmanagementbackend.employee.EmployeeRepository;
        import pl.polsl.repairmanagementbackend.employee.EmployeeUserRole;

        import java.util.HashSet;

@Component
public class DataLoader implements ApplicationRunner {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public DataLoader(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {

//        var grantedAuthorities = new HashSet<GrantedAuthority>();
//
//        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + "ADM"));
//        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + "MAN"));
//        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + "WRK"));
//
//        Authentication auth =
//                new UsernamePasswordAuthenticationToken(null, null, grantedAuthorities);

       // SecurityContextHolder.getContext().setAuthentication(auth);

        var adminEmployee = new EmployeeEntity("Admin", "Admin", "000000000",
                EmployeeUserRole.ADMIN.toString(), "admin", new BCryptPasswordEncoder().encode("pass"),
                new AddressEntity("11-111", "Adminowo", "Adminska", "11"));
        employeeRepository.save(adminEmployee);

        var workerEmployee = new EmployeeEntity("Worker", "Worker", "000000001",
                EmployeeUserRole.WORKER.toString(), "worker", new BCryptPasswordEncoder().encode("pass"),
                new AddressEntity("11-111", "Workerowo", "Workerska", "11"));
        employeeRepository.save(workerEmployee);

        var managerEmployee = new EmployeeEntity("Manager", "Manager", "000000002",
                EmployeeUserRole.MANAGER.toString(), "manager", new BCryptPasswordEncoder().encode("pass"),
                new AddressEntity("11-111", "Managerowo", "Managerska", "11"));
        employeeRepository.save(managerEmployee);

        //SecurityContextHolder.getContext().setAuthentication(null);
    }
}


