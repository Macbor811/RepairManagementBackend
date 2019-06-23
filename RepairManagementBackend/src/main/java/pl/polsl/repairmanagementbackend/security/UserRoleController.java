package pl.polsl.repairmanagementbackend.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.polsl.repairmanagementbackend.employee.EmployeeUser;
import pl.polsl.repairmanagementbackend.employee.EmployeeUserDetailsService;

import java.security.Principal;

@Controller
public class UserRoleController {

    private class UserData {

        private String id;
        private String role;

        public UserData(String id, String role) {
            this.id = id;
            this.role = role;
        }

        public String getId() {
            return id;
        }
        public String getRole() {
            return role;
        }
    }

    private final EmployeeUserDetailsService userDetailsService;

    @Autowired
    public UserRoleController(EmployeeUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @RequestMapping("/fuser")
    public String home(Principal user) {
        return "Hello " + user.getName();
    }

    @GetMapping("/oauth/user")
    @ResponseBody
    private UserData getLoggedInUserData(Principal principal){
//        return userDetailsService
//                .loadUserByUsername(principal.getName())
//                .getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .filter(a -> (a.startsWith("ROLE_")))
//                .findFirst().orElse("ERROR");

        EmployeeUser user = (EmployeeUser) userDetailsService.loadUserByUsername(principal.getName());

        UserData userData = new UserData(String.valueOf(user.getId()), user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(a -> (a.startsWith("ROLE_")))
                .findFirst().orElse("ERROR"));

        return userData;
    }
}
