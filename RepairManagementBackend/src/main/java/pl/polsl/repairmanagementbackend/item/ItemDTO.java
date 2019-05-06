package pl.polsl.repairmanagementbackend.item;

import pl.polsl.repairmanagementbackend.DTO;
import pl.polsl.repairmanagementbackend.customer.CustomerDTO;
import pl.polsl.repairmanagementbackend.itemtype.ItemTypeDTO;

public class ItemDTO{

    private Integer id;
    private String name;


    private Integer itemTypeId;
    private Integer ownerId;

//    public ItemDTO(Integer id, String name, Integ itemType, CustomerDTO owner) {
//        this.id = id;
//        this.name = name;
//        this.itemType = itemType;
//        this.owner = owner;
//    }


//    @Override
//    public ItemEntity toEntity() {
//        return new ItemEntity(name, itemType.toEntity(), owner.toEntity());
//    }

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


    public Integer getItemTypeId() {
        return itemTypeId;
    }
    public void setItemTypeId(Integer itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }
    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }



}
