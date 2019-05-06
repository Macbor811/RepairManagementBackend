package pl.polsl.repairmanagementbackend.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.repairmanagementbackend.customer.CustomerEntity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {


    @Transactional
    public CustomerEntity save(CustomerEntity customer);


    @Transactional
    List<CustomerEntity> findAll();

    @Transactional
    public Optional<CustomerEntity> findById(Integer id);
}
