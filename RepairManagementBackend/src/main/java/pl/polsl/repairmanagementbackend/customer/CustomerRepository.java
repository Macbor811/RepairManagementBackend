package pl.polsl.repairmanagementbackend.customer;

        import com.querydsl.core.types.dsl.StringPath;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.data.domain.Page;
        import org.springframework.data.domain.Pageable;
        import org.springframework.data.domain.Sort;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.querydsl.QuerydslPredicateExecutor;
        import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
        import org.springframework.data.querydsl.binding.QuerydslBindings;
        import org.springframework.data.rest.core.annotation.RepositoryRestResource;
        import org.springframework.security.access.prepost.PreAuthorize;
        import org.springframework.stereotype.Repository;
        import org.springframework.transaction.annotation.Transactional;
        import org.springframework.web.bind.annotation.PathVariable;
        import pl.polsl.repairmanagementbackend.customer.QCustomerEntity;

        import javax.persistence.EntityManager;
        import javax.persistence.NoResultException;
        import java.util.List;
        import java.util.Optional;

@Repository
@RepositoryRestResource(collectionResourceRel = "customer", path = "customer")
//@PreAuthorize("hasRole('ADM')")
public interface CustomerRepository extends
        JpaRepository<CustomerEntity, Integer>,
        QuerydslPredicateExecutor<CustomerEntity>,
        QuerydslBinderCustomizer<QCustomerEntity> {


    @Override
    default void customize(QuerydslBindings bindings, QCustomerEntity root) {

        bindings.bind(String.class).first((StringPath path, String value) -> path.startsWithIgnoreCase(value));
    }

    //@PreAuthorize("hasRole('BL')")
    Page<CustomerEntity> findAll(Pageable pageable);

    //@PreAuthorize("hasRole('b')")
    List<CustomerEntity> findAll();

    //@PreAuthorize("hasRole('b')")
    List<CustomerEntity> findAll(Sort sort);


//    @Transactional
//     CustomerEntity save(CustomerEntity customer);
//
//
//    @Transactional
//    List<CustomerEntity> findAll();
//
   // @Transactional
    @PreAuthorize("hasRole('MAN') || (hasRole('ADM') && #id == principal.id)")
    Optional<CustomerEntity> findById(@PathVariable("id") Integer id);
}
