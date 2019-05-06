package pl.polsl.repairmanagementbackend.activity;

import pl.polsl.repairmanagementbackend.DTO;
import pl.polsl.repairmanagementbackend.activitytype.ActivityTypeDTO;
import pl.polsl.repairmanagementbackend.employee.EmployeeDTO;
import pl.polsl.repairmanagementbackend.request.RequestDTO;

import java.sql.Timestamp;

public class ActivityDTO{

    private Integer id;
    private Integer sequenceNum;
    private String description;
    private String result;
    private String status;
    private Timestamp registerDate;
    private Timestamp endDate;
    private RequestDTO request;
    private EmployeeDTO worker;
    private ActivityTypeDTO activityType;

    public ActivityDTO(Integer id, Integer sequenceNum, String description, String result, String status, Timestamp registerDate, Timestamp endDate, RequestDTO request, EmployeeDTO worker, ActivityTypeDTO activityType) {
        this.id = id;
        this.sequenceNum = sequenceNum;
        this.description = description;
        this.result = result;
        this.status = status;
        this.registerDate = registerDate;
        this.endDate = endDate;
        this.request = request;
        this.worker = worker;
        this.activityType = activityType;
    }


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

    public EmployeeDTO getWorker() {
        return worker;
    }

    public void setWorker(EmployeeDTO worker) {
        this.worker = worker;
    }

    public ActivityTypeDTO getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityTypeDTO activityType) {
        this.activityType = activityType;
    }


//    @Override
//    public ActivityEntity toEntity() {
//
//        return new ActivityEntity(sequenceNum, description, result, status, registerDate, endDate, activityType.toEntity(),
//                request.toEntity(), worker.toEntity());
//    }
}
