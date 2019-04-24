package pl.polsl.repairmanagementbackend.item;

import pl.polsl.repairmanagementbackend.DTO;
import pl.polsl.repairmanagementbackend.Entity;
import pl.polsl.repairmanagementbackend.customer.CustomerDTO;
import pl.polsl.repairmanagementbackend.itemtype.ItemTypeDTO;
import pl.polsl.repairmanagementbackend.request.RequestDTO;

import java.util.Collection;

public class ItemDTO implements DTO {

    private Integer id;
    private String name;
    private ItemTypeDTO itemType;
    private CustomerDTO owner;

    public ItemDTO(Integer id, String name, ItemTypeDTO itemType, CustomerDTO owner) {
        this.id = id;
        this.name = name;
        this.itemType = itemType;
        this.owner = owner;
    }


    @Override
    public ItemEntity toEntity() {
        return new ItemEntity(name, itemType.toEntity(), owner.toEntity());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemTypeDTO getItemType() {
        return itemType;
    }

    public void setItemType(ItemTypeDTO itemType) {
        this.itemType = itemType;
    }

    public CustomerDTO getOwner() {
        return owner;
    }

    public void setOwner(CustomerDTO owner) {
        this.owner = owner;
    }
}
