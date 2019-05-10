package pl.polsl.repairmanagementbackend.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RepositoryRestResource(collectionResourceRel = "request", path = "request")
public interface RequestRepository extends JpaRepository<RequestEntity, Integer> {


    @Transactional
    public RequestEntity save(RequestEntity request);


    @Transactional
    List<RequestEntity> findAll();

    @Transactional
    RequestEntity findById(int id);

}
