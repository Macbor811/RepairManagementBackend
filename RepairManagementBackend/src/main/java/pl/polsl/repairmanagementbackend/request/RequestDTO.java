package pl.polsl.repairmanagementbackend.request;

import pl.polsl.repairmanagementbackend.DTO;
import pl.polsl.repairmanagementbackend.activity.ActivityDTO;
import pl.polsl.repairmanagementbackend.item.ItemDTO;
import pl.polsl.repairmanagementbackend.employee.EmployeeDTO;
import pl.polsl.repairmanagementbackend.item.ItemEntity;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.stream.Collectors;

public class RequestDTO implements DTO {

    private Integer id;
    private String description;
    private String result;
    private String status;
    private Timestamp registerDate;
    private Timestamp endDate;
    private Collection<ActivityDTO> activities;
    private ItemDTO item;
    private EmployeeDTO manager;


    public RequestDTO(Integer id, String description, String result, String status, Timestamp registerDate, Timestamp endDate, Collection<ActivityDTO> activities, ItemDTO item, EmployeeDTO manager) {
        this.id = id;
        this.description = description;
        this.result = result;
        this.status = status;
        this.registerDate = registerDate;
        this.endDate = endDate;
        this.activities = activities;
        this.item = item;
        this.manager = manager;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Timestamp registerDate) {
        this.registerDate = registerDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Collection<ActivityDTO> getActivities() {
        return activities;
    }

    public void setActivities(Collection<ActivityDTO> activities) {
        this.activities = activities;
    }

    public ItemDTO getItem() {
        return item;
    }

    public void setItem(ItemDTO item) {
        this.item = item;
    }

    public EmployeeDTO getManager() {
        return manager;
    }

    public void setManager(EmployeeDTO manager) {
        this.manager = manager;
    }

    @Override
    public RequestEntity toEntity(){
        return new RequestEntity(description, result, status, registerDate, endDate,
                activities.stream().map(ActivityDTO::toEntity).collect(Collectors.toList()),
                item.toEntity(),
                manager.toEntity());
    }
}
