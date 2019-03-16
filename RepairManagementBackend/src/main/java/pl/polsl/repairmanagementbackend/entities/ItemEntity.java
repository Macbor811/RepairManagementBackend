package pl.polsl.repairmanagementbackend.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "item", schema = "public", catalog = "postgres")
public class ItemEntity {
    private Integer id;
    private String name;
    private ItemTypeEntity itemTypeByItemTypeId;
    private ClientEntity clientByOwnerId;
    private Collection<RequestEntity> requestsById;

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemEntity that = (ItemEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @ManyToOne
    @JoinColumn(name = "item_type_id", referencedColumnName = "id", nullable = false)
    public ItemTypeEntity getItemTypeByItemTypeId() {
        return itemTypeByItemTypeId;
    }

    public void setItemTypeByItemTypeId(ItemTypeEntity itemTypeByItemTypeId) {
        this.itemTypeByItemTypeId = itemTypeByItemTypeId;
    }

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false)
    public ClientEntity getClientByOwnerId() {
        return clientByOwnerId;
    }

    public void setClientByOwnerId(ClientEntity clientByOwnerId) {
        this.clientByOwnerId = clientByOwnerId;
    }

    @OneToMany(mappedBy = "itemByItemId")
    public Collection<RequestEntity> getRequestsById() {
        return requestsById;
    }

    public void setRequestsById(Collection<RequestEntity> requestsById) {
        this.requestsById = requestsById;
    }
}
