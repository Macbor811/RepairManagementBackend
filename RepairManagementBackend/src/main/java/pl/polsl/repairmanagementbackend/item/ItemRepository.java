package pl.polsl.repairmanagementbackend.item;

        import com.querydsl.core.types.dsl.StringPath;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.querydsl.QuerydslPredicateExecutor;
        import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
        import org.springframework.data.querydsl.binding.QuerydslBindings;
        import org.springframework.data.rest.core.annotation.RepositoryRestResource;
        import org.springframework.stereotype.Repository;
        import org.springframework.transaction.annotation.Transactional;
        import org.springframework.web.bind.annotation.CrossOrigin;

        import java.util.List;
        import java.util.Optional;

@Repository
@RepositoryRestResource(collectionResourceRel = "item", path = "item")
@CrossOrigin
public interface ItemRepository extends
        JpaRepository<ItemEntity, Integer>,
        QuerydslPredicateExecutor<ItemEntity>,
        QuerydslBinderCustomizer<QItemEntity> {

    @Override
    default void customize(QuerydslBindings bindings, QItemEntity root) {

        bindings.bind(String.class).first((StringPath path, String value) -> path.startsWithIgnoreCase(value));
    }

    @Transactional
    ItemEntity save(ItemEntity item);


    @Transactional
    List<ItemEntity> findAll();

    @Transactional
    Optional<ItemEntity> findById(Integer id);
}
