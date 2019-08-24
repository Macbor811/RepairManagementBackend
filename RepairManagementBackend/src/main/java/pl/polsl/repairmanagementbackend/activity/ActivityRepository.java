package pl.polsl.repairmanagementbackend.activity;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.StringPath;
import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
@RepositoryRestResource(collectionResourceRel = "activity", path = "activity")
@CrossOrigin
public interface ActivityRepository extends
        JpaRepository<ActivityEntity, Integer>,
        QuerydslPredicateExecutor<ActivityEntity>,
        QuerydslBinderCustomizer<QActivityEntity> {


    @Override
    default void customize(QuerydslBindings bindings, QActivityEntity root) {

        bindings.bind(String.class).all((StringPath path, Collection<? extends String> values) -> {
            BooleanBuilder predicate = new BooleanBuilder();
            values.forEach( value -> predicate.or(path.startsWithIgnoreCase(value) ));
            return Optional.of(predicate);
        });

        bindings.bind(root.registerDate).first((path,  value) -> path.between(value, value.plus(1, ChronoUnit.DAYS)));
        bindings.bind(root.endDate).first((path,  value) -> path.between(value, value.plus(1, ChronoUnit.DAYS)));
    }


    @Query("SELECT coalesce(max(ae.sequenceNum), 0) FROM ActivityEntity ae")
    Integer getMaxSequenceNum();

}
