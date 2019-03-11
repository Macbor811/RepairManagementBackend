package pl.polsl.repairmanagment;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.polsl.repairmanagment.entities.Client;
import pl.polsl.repairmanagment.entities.ClientRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepairManagmentApplicationTests {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    @Qualifier("defaultName")
    private String name;

    @Test
    public void contextLoads() {
        Client client = new Client();
        client.setName(name);

        clientRepository.save(client);

        Assert.assertEquals(1, clientRepository.findByName("nazwa").size());
    }

}
