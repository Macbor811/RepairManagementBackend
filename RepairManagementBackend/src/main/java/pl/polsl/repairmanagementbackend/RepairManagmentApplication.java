package pl.polsl.repairmanagementbackend;


import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import pl.polsl.repairmanagementbackend.springsocial.config.AppProperties;

import java.sql.SQLException;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
//@EnableSpringDataWebSupport
public class RepairManagmentApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        startH2Server();
        SpringApplication.run(
                RepairManagmentApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        startH2Server();
        return application.sources(RepairManagmentApplication.class);
    }

    private static void startH2Server() {
        try {
            Server h2Server = Server.createTcpServer("-ifNotExists").start();
            if (h2Server.isRunning(true)) {
                System.out.println("H2 server was started and is running.");
            } else {
                throw new RuntimeException("Could not start H2 server.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to start H2 server: ", e);
        }
    }
}
