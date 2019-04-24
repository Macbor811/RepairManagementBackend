package pl.polsl.repairmanagementbackend.itemtype;

import pl.polsl.repairmanagementbackend.DTO;
import pl.polsl.repairmanagementbackend.Entity;

public class ItemTypeDTO implements DTO {

    private Integer id;
    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public ItemTypeEntity toEntity() {

        ItemTypeEntity entity = new ItemTypeEntity();
        entity.setType(type);

        return entity;
    }
}
