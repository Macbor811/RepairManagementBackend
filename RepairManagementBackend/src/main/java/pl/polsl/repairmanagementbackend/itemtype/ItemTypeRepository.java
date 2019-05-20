package pl.polsl.repairmanagementbackend.itemtype;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RepositoryRestResource(collectionResourceRel = "item-type", path = "item-type")
public interface ItemTypeRepository extends JpaRepository<ItemTypeEntity, String> {

    @Transactional
    ItemTypeEntity save(ItemTypeEntity item);


    @Transactional
    List<ItemTypeEntity> findAll();

    @Transactional
    Optional<ItemTypeEntity> findById(String id);
}
