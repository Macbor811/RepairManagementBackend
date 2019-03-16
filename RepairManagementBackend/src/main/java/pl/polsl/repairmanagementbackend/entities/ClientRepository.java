package pl.polsl.repairmanagementbackend.entities;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
public class ClientRepository  {

    private final EntityManager entityManager;

    public ClientRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void save(ClientEntity client) {
        entityManager.persist(client);
    }


}
