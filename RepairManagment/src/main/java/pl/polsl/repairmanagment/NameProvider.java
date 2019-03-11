package pl.polsl.repairmanagment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class NameProvider {

    private final String name;

    public NameProvider(@Value("${name.default}") String name) {
        this.name = name;
    }

    @Bean("defaultName")
    String getName() {
        return name;
    }
}
