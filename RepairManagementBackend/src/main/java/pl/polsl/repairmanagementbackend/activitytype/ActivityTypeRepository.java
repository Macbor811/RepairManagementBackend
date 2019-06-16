package pl.polsl.repairmanagementbackend.activitytype;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@Repository
@RepositoryRestResource(collectionResourceRel = "activity-type", path = "activity-type")
@CrossOrigin
public interface ActivityTypeRepository extends
        JpaRepository<ActivityTypeEntity, Integer> {

    @Transactional
    ActivityTypeEntity save(ActivityTypeEntity activity);


    @Transactional
    List<ActivityTypeEntity> findAll();

    @Transactional
    Optional<ActivityTypeEntity> findById(Integer id);
}

