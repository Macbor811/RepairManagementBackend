package pl.polsl.repairmanagementbackend.activity;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.StringPath;
import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDateTime;
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

        bindings.bind(String.class).first((StringPath path, String value) -> path.startsWithIgnoreCase(value));

        bindings.bind(root.registerDate).first((path,  value) -> { return (path.dayOfMonth().eq(value.getDayOfMonth()));});
        bindings.bind(root.endDate).first((path,  value) -> { return (path.dayOfMonth().eq(value.getDayOfMonth()));});

    }


    @Transactional
    ActivityEntity save(ActivityEntity activity);


    @Transactional
    List<ActivityEntity> findAll();

    @Transactional
    ActivityEntity findById(int id);

}
