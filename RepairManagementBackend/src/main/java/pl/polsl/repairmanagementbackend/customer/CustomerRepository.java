package pl.polsl.repairmanagementbackend.customer;

        import com.querydsl.core.BooleanBuilder;
        import com.querydsl.core.types.dsl.StringPath;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.querydsl.QuerydslPredicateExecutor;
        import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
        import org.springframework.data.querydsl.binding.QuerydslBindings;
        import org.springframework.data.rest.core.annotation.RepositoryRestResource;
        import org.springframework.stereotype.Repository;
        import org.springframework.transaction.annotation.Transactional;
        import org.springframework.web.bind.annotation.CrossOrigin;
        import pl.polsl.repairmanagementbackend.customer.QCustomerEntity;

        import javax.persistence.EntityManager;
        import javax.persistence.NoResultException;
        import java.util.Collection;
        import java.util.List;
        import java.util.Optional;

@Repository
@RepositoryRestResource(collectionResourceRel = "customer", path = "customer")
@CrossOrigin
public interface CustomerRepository extends
        JpaRepository<CustomerEntity, Integer>,
        QuerydslPredicateExecutor<CustomerEntity>,
        QuerydslBinderCustomizer<QCustomerEntity> {


    @Override
    default void customize(QuerydslBindings bindings, QCustomerEntity root) {

        bindings.bind(String.class).all((StringPath path, Collection<? extends String> values) -> {
            BooleanBuilder predicate = new BooleanBuilder();
            values.forEach( value -> predicate.or(path.startsWithIgnoreCase(value) ));
            return Optional.of(predicate);
        });
    }

//    @Transactional
//     CustomerEntity save(CustomerEntity customer);
//
//
//    @Transactional
//    List<CustomerEntity> findAll();
//
//    @Transactional
//    public Optional<CustomerEntity> findById(Integer id);
}
