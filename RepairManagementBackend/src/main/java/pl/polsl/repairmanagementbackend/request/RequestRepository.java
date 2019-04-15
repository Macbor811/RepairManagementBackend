package pl.polsl.repairmanagementbackend.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class RequestRepository {
    private final EntityManager entityManager;

    @Autowired
    public RequestRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Transactional
    public Integer save(RequestEntity request) {
        entityManager.persist(request);
        return request.getId();
    }


    @Transactional
    List<RequestEntity> findAll(){
        return entityManager
                .createQuery("SELECT r FROM request r")
                .getResultList();
    }

    @Transactional
    RequestEntity findById(int id){


        return (RequestEntity) entityManager
                .createQuery("SELECT r FROM request r WHERE r.id = :id")
                .setParameter("id", id)
                .getSingleResult();
    }

}
