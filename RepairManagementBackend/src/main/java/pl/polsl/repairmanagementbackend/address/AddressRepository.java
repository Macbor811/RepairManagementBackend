package pl.polsl.repairmanagementbackend.address;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import java.util.List;


@Repository
public class AddressRepository {


    private final EntityManager entityManager;

    @Autowired
    public AddressRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void save(AddressEntity address) {
        entityManager.persist(address);
    }


    @Transactional
    List<AddressEntity> findAll(){
       return entityManager.createQuery("SELECT a FROM address a").getResultList();
    }

    @Transactional
    AddressEntity findById(int id){
        return (AddressEntity)entityManager.createQuery("SELECT a FROM address a WHERE a.id = :id").setParameter("id", id).getSingleResult();
    }
}

