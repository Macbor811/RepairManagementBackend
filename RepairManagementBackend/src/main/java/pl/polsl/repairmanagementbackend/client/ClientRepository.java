package pl.polsl.repairmanagementbackend.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.repairmanagementbackend.client.ClientEntity;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ClientRepository  {

    private final EntityManager entityManager;

    @Autowired
    public ClientRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void save(ClientEntity client) {
        entityManager.persist(client);
    }


    @Transactional
    List<ClientEntity> findAll(){
        return entityManager.createQuery("SELECT c FROM client c").getResultList();
    }

}
