package pl.polsl.repairmanagementbackend.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RepositoryRestResource(collectionResourceRel = "addresses", path = "address")
public interface AddressRepository extends PagingAndSortingRepository<AddressEntity, Integer> {

   // @Autowired
   // public final EntityManager entityManager;

//    public AddressRepository(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }
//
//    @Transactional
//    public void save(AddressEntity address) {
//        entityManager.persist(address);
//    }

    List<AddressEntity> findAll();

    @GetMapping("/byid")
    @ResponseBody
    List<AddressEntity> findAllById(@RequestParam Integer id);
}

