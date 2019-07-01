package pl.polsl.repairmanagementbackend.springsocial.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import pl.polsl.repairmanagementbackend.springsocial.model.SocialUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RepositoryRestResource(collectionResourceRel = "social-user", path = "social-user")
@CrossOrigin
public interface SocialUserRepository extends JpaRepository<SocialUserEntity, Long> {

    Optional<SocialUserEntity> findByEmail(String email);

    Boolean existsByEmail(String email);

}
