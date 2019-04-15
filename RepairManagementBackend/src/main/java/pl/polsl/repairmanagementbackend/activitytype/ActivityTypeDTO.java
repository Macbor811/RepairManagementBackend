package pl.polsl.repairmanagementbackend.activitytype;

import pl.polsl.repairmanagementbackend.DTO;
import pl.polsl.repairmanagementbackend.activity.ActivityEntity;

public class ActivityTypeDTO implements DTO {
    private Integer id;
    private String type;


    public ActivityTypeDTO(Integer id, String type){
        this.id = id;
        this.type = type;
    }

    @Override
    public ActivityTypeEntity toEntity(){
        return new ActivityTypeEntity(type, null);
    }
}
