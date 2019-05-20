package pl.polsl.repairmanagementbackend;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
//@EnableSpringDataWebSupport
public class RepairManagmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(RepairManagmentApplication.class, args);
    }

}
