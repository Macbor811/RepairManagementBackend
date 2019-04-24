package pl.polsl.repairmanagementbackend.activity;

import pl.polsl.repairmanagementbackend.DTO;
import pl.polsl.repairmanagementbackend.activitytype.ActivityTypeEntity;
import pl.polsl.repairmanagementbackend.employee.EmployeeEntity;
import pl.polsl.repairmanagementbackend.request.RequestEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "activity", schema = "public", catalog = "postgres")
public class ActivityEntity implements pl.polsl.repairmanagementbackend.Entity {
    private Integer id;
    private Integer sequenceNum;
    private String description;
    private String result;
    private String status;
    private Timestamp registerDate;
    private Timestamp endDate;
    private ActivityTypeEntity activityType;
    private RequestEntity request;
    private EmployeeEntity worker;

    public ActivityEntity(){};

    public ActivityEntity(Integer sequenceNum, String description, String result, String status, Timestamp registerDate, Timestamp endDate, ActivityTypeEntity activityType, RequestEntity request, EmployeeEntity worker) {
        this.sequenceNum = sequenceNum;
        this.description = description;
        this.result = result;
        this.status = status;
        this.registerDate = registerDate;
        this.endDate = endDate;
        this.activityType = activityType;
        this.request = request;
        this.worker = worker;
    }

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "sequence_num", nullable = true)
    public Integer getSequenceNum() {
        return sequenceNum;
    }

    public void setSequenceNum(Integer sequenceNum) {
        this.sequenceNum = sequenceNum;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 1024)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "result", nullable = true, length = 3)
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Basic
    @Column(name = "status", nullable = true, length = 3)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "register_date", nullable = true)
    public Timestamp getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Timestamp registerDate) {
        this.registerDate = registerDate;
    }

    @Basic
    @Column(name = "end_date", nullable = true)
    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityEntity that = (ActivityEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(sequenceNum, that.sequenceNum) &&
                Objects.equals(description, that.description) &&
                Objects.equals(result, that.result) &&
                Objects.equals(status, that.status) &&
                Objects.equals(registerDate, that.registerDate) &&
                Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sequenceNum, description, result, status, registerDate, endDate);
    }

    @ManyToOne
    @JoinColumn(name = "request_id", referencedColumnName = "id", nullable = false)
    public RequestEntity getRequest() {
        return request;
    }

    public void setRequest(RequestEntity request) {
        this.request = request;
    }

    @ManyToOne
    @JoinColumn(name = "worker_id", referencedColumnName = "id", nullable = false)
    public EmployeeEntity getWorker() {
        return worker;
    }

    public void setWorker(EmployeeEntity worker) {
        this.worker = worker;
    }


    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id", nullable = false)
    public ActivityTypeEntity getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityTypeEntity activityType) {
        this.activityType = activityType;
    }

    @Override
    public ActivityDTO toDTO() {
        return null;
    }
}
