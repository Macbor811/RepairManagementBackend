package pl.polsl.repairmanagementbackend;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.polsl.repairmanagementbackend.client.ClientRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepairmanagementbackendApplicationTests {

    @Autowired
    private ClientRepository clientRepository;


    @Test
    public void contextLoads() {

       // Assert.assertEquals(1, clientRepository.findByName("nazwa").size());
    }

}
