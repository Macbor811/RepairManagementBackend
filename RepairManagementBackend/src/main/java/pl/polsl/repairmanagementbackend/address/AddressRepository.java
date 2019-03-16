package pl.polsl.repairmanagementbackend.address;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import java.util.List;


@Repository
public class AddressRepository{

    @Autowired
    private final EntityManager entityManager;

    public AddressRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void save(AddressEntity address) {
        entityManager.persist(address);
    }


    List<AddressEntity> findAll(){
       return entityManager.createQuery("SELECT a FROM address a").getResultList();
    };

    //List<AddressEntity> findAllById(@RequestParam Integer id);
}

