package pl.polsl.repairmanagementbackend.employee;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.web.bind.annotation.CrossOrigin;
import pl.polsl.repairmanagementbackend.customer.CustomerEntity;

import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
@RepositoryRestResource(collectionResourceRel = "employee", path = "employee")
@CrossOrigin
//@PreAuthorize("hasAnyRole('ADM', 'MAN')")
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

        bindings.bind(root.deactivationDate).first((path,  value) -> path.between(value, value.plus(1, ChronoUnit.DAYS)));

    }

    EmployeeEntity save(EmployeeEntity Employee);


    List<EmployeeEntity> findAll();

    @PreAuthorize("(hasRole('WRK') && #id == principal.id) || hasRole('MAN') ||  hasRole('ADM')")
    Optional<EmployeeEntity> findById(Integer id);

    Optional<EmployeeEntity> findByUsername(String username);

    Page<EmployeeEntity> findByFirstNameStartsWithIgnoreCaseOrLastNameStartsWithIgnoreCase(String firstName, String lastName, Pageable pageable);

    Page<EmployeeEntity> findByFirstNameStartsWithIgnoreCaseAndLastNameStartsWithIgnoreCase(String firstName, String lastName, Pageable pageable);

}