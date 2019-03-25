package pl.polsl.repairmanagementbackend.item;

import pl.polsl.repairmanagementbackend.client.ClientEntity;
import pl.polsl.repairmanagementbackend.itemtype.ItemTypeEntity;
import pl.polsl.repairmanagementbackend.request.RequestEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "item", schema = "public", catalog = "postgres")
public class ItemEntity {
    private Integer id;
    private String name;
    private ItemTypeEntity itemType;
    private ClientEntity owner;
    private Collection<RequestEntity> requests;

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
    public ItemTypeEntity getItemType() {
        return itemType;
    }

    public void setItemType(ItemTypeEntity itemType) {
        this.itemType = itemType;
    }

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false)
    public ClientEntity getOwner() {
        return owner;
    }

    public void setOwner(ClientEntity owner) {
        this.owner = owner;
    }

    @OneToMany(mappedBy = "item")
    public Collection<RequestEntity> getRequests() {
        return requests;
    }

    public void setRequests(Collection<RequestEntity> requests) {
        this.requests = requests;
    }
}
