package pl.polsl.repairmanagementbackend.employee;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
@RepositoryRestResource(collectionResourceRel = "employee", path = "employee")
@CrossOrigin
public interface EmployeeRepository extends
        JpaRepository<EmployeeEntity, Integer>,
        QuerydslPredicateExecutor<EmployeeEntity>,
        QuerydslBinderCustomizer<QEmployeeEntity>{

    @Override
    default void customize(QuerydslBindings bindings, QEmployeeEntity root) {

        bindings.bind(String.class).all((StringPath path, Collection<? extends String> values) -> {
            BooleanBuilder predicate = new BooleanBuilder();
            values.forEach( value -> predicate.or(path.startsWithIgnoreCase(value) ));
            return Optional.of(predicate);
        });
    }

    @Transactional
    EmployeeEntity save(EmployeeEntity Employee);


    @Transactional
    List<EmployeeEntity> findAll();

    @Transactional
    @PreAuthorize("hasRole('MAN') || (hasRole('WRK') && #id == principal.id)")
    Optional<EmployeeEntity> findById(Integer id);

    @Transactional
    Optional<EmployeeEntity> findByUsername(String username);
}