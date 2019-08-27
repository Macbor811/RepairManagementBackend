package pl.polsl.repairmanagementbackend.activitytype;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
@RepositoryRestResource(collectionResourceRel = "activity-type", path = "activity-type")
@CrossOrigin
public interface ActivityTypeRepository extends
        JpaRepository<ActivityTypeEntity, Integer>,
        QuerydslPredicateExecutor<ActivityTypeEntity>,
        QuerydslBinderCustomizer<QActivityTypeEntity> {

    Optional<ActivityTypeEntity> findByType(String type);

    @Override
    default void customize(QuerydslBindings bindings, QActivityTypeEntity root) {

        bindings.bind(String.class).all((StringPath path, Collection<? extends String> values) -> {
            BooleanBuilder predicate = new BooleanBuilder();
            values.forEach( value -> predicate.or(path.startsWithIgnoreCase(value) ));
            return Optional.of(predicate);
        });

    }

}

