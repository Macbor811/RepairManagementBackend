package pl.polsl.repairmanagment.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "item", schema = "public", catalog = "postgres")
public class ItemEntity {
    private int id;
    private String name;
    private ItemTypeEntity itemTypeByItemTypeId;
    private ClientEntity clientByOwnerId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 100)
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
        return id == that.id &&
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
}
