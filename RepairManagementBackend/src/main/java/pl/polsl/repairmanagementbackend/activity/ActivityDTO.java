package pl.polsl.repairmanagementbackend.activity;

import pl.polsl.repairmanagementbackend.DTO;
import pl.polsl.repairmanagementbackend.Entity;
import pl.polsl.repairmanagementbackend.activitytype.ActivityTypeDTO;
import pl.polsl.repairmanagementbackend.employee.EmployeeDTO;
import pl.polsl.repairmanagementbackend.request.RequestDTO;

import java.sql.Timestamp;

public class ActivityDTO implements DTO {

    private Integer id;
    private Integer sequenceNum;
    private String description;
    private String result;
    private String status;
    private Timestamp registerDate;
    private Timestamp endDate;
    private RequestDTO request;
    private EmployeeDTO employee;
    private ActivityTypeDTO activityType;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSequenceNum() {
        return sequenceNum;
    }

    public void setSequenceNum(Integer sequenceNum) {
        this.sequenceNum = sequenceNum;
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

    public RequestDTO getRequest() {
        return request;
    }

    public void setRequest(RequestDTO request) {
        this.request = request;
    }

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDTO employee) {
        this.employee = employee;
    }

    public ActivityTypeDTO getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityTypeDTO activityType) {
        this.activityType = activityType;
    }


    @Override
    public ActivityEntity toEntity() {
        return null;
    }
}
