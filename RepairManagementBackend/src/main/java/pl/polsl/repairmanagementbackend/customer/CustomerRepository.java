package pl.polsl.repairmanagementbackend.customer;

import com.querydsl.core.types.dsl.StringPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.repairmanagementbackend.customer.CustomerEntity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository
@RepositoryRestResource(collectionResourceRel = "customer", path = "customer")
public interface CustomerRepository extends
        JpaRepository<CustomerEntity, Integer>,
        QuerydslPredicateExecutor<CustomerEntity>,
        QuerydslBinderCustomizer<QCustomerEntity> {


    @Override
    default public void customize(QuerydslBindings bindings, QCustomerEntity user) {

        bindings.bind(String.class).first((StringPath path, String value) -> path.startsWithIgnoreCase(value));
    }

    @Transactional
    public CustomerEntity save(CustomerEntity customer);


    @Transactional
    List<CustomerEntity> findAll();

    @Transactional
    public Optional<CustomerEntity> findById(Integer id);
}
