package pl.polsl.repairmanagementbackend.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pl.polsl.repairmanagementbackend.DTO;
import pl.polsl.repairmanagementbackend.activity.ActivityDTO;
import pl.polsl.repairmanagementbackend.employee.EmployeeDTO;
import pl.polsl.repairmanagementbackend.item.ItemDTO;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.stream.Collectors;

public class RequestSaveable{

    private String description;
    private String result;
    private String status;
    //private Timestamp registerDate;
    //private Timestamp endDate;

   // @JsonIgnore
    private Integer itemId;
   // @JsonIgnore
    private Integer managerId;



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

//    public Timestamp getRegisterDate() {
//        return registerDate;
//    }
//    public void setRegisterDate(Timestamp registerDate) {
//        this.registerDate = registerDate;
//    }
//
//    public Timestamp getEndDate() {
//        return endDate;
//    }
//    public void setEndDate(Timestamp endDate) {
//        this.endDate = endDate;
//    }

    public Integer getItemId() {
        return itemId;
    }
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getManagerId() {
        return managerId;
    }
    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

}
