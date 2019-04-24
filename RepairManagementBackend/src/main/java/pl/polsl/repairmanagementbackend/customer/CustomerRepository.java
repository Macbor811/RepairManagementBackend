package pl.polsl.repairmanagementbackend.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.repairmanagementbackend.customer.CustomerEntity;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CustomerRepository  {

    private final EntityManager entityManager;

    @Autowired
    public CustomerRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public Integer save(CustomerEntity customer) {
        entityManager.persist(customer);
        return customer.getId();
    }


    @Transactional
    List<CustomerEntity> findAll(){
        return entityManager
                .createQuery("SELECT c FROM customer c")
                .getResultList();
    }

    @Transactional
    public CustomerEntity findById(int id){


        return (CustomerEntity) entityManager
                .createQuery("SELECT c FROM customer c WHERE c.id = :id")
                .setParameter("id", id)
                .getSingleResult();
    }

}
