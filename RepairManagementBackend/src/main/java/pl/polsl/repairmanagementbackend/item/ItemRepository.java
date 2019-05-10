package pl.polsl.repairmanagementbackend.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RepositoryRestResource(collectionResourceRel = "item", path = "item")
public interface ItemRepository extends JpaRepository<ItemEntity, Integer> {


    @Transactional
    public ItemEntity save(ItemEntity item);


    @Transactional
    List<ItemEntity> findAll();

    @Transactional
    public Optional<ItemEntity> findById(Integer id);
}
