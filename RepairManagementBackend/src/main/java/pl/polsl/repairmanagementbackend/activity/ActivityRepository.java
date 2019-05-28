package pl.polsl.repairmanagementbackend.activity;

import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RepositoryRestResource(collectionResourceRel = "activity", path = "activity")
public interface ActivityRepository extends
        JpaRepository<ActivityEntity, Integer>,
        QuerydslPredicateExecutor<ActivityEntity>,
        QuerydslBinderCustomizer<QActivityEntity> {


    @Override
    default void customize(QuerydslBindings bindings, QActivityEntity root) {

        bindings.bind(String.class).first((StringPath path, String value) -> path.startsWithIgnoreCase(value));
    }


    @Transactional
    ActivityEntity save(ActivityEntity activity);


    @Transactional
    List<ActivityEntity> findAll();

    @Transactional
    ActivityEntity findById(int id);

}
