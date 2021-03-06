package pl.polsl.repairmanagementbackend.request;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
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
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
@RepositoryRestResource(collectionResourceRel = "request", path = "request")
@CrossOrigin
public interface RequestRepository extends
        JpaRepository<RequestEntity, Integer>,
        QuerydslPredicateExecutor<RequestEntity>,
        QuerydslBinderCustomizer<QRequestEntity> {

    @Override
    default void customize(QuerydslBindings bindings, QRequestEntity root) {


        bindings.bind(String.class).all((StringPath path, Collection<? extends String> values) -> {
            BooleanBuilder predicate = new BooleanBuilder();
            values.forEach( value -> predicate.or(path.startsWithIgnoreCase(value) ));
            return Optional.of(predicate);
        });


        bindings.bind(root.registerDate).first((path,  value) -> path.between(value, value.plus(1, ChronoUnit.DAYS)));
        bindings.bind(root.endDate).first((path,  value) -> path.between(value, value.plus(1, ChronoUnit.DAYS)));
    }

    @Transactional
    RequestEntity save(RequestEntity request);


    @Transactional
    List<RequestEntity> findAll();

    @Transactional
    RequestEntity findById(int id);

}
