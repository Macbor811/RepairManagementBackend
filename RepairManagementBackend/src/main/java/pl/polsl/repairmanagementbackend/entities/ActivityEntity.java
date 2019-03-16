package pl.polsl.repairmanagementbackend.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "activity", schema = "public", catalog = "postgres")
public class ActivityEntity {
    private Integer id;
    private Integer sequenceNum;
    private String description;
    private String result;
    private String status;
    private Timestamp registerDate;
    private Timestamp endDate;
    private RequestEntity requestByRequestId;
    private PersonnelEntity personnelByWorkerId;

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
    public RequestEntity getRequestByRequestId() {
        return requestByRequestId;
    }

    public void setRequestByRequestId(RequestEntity requestByRequestId) {
        this.requestByRequestId = requestByRequestId;
    }

    @ManyToOne
    @JoinColumn(name = "worker_id", referencedColumnName = "id", nullable = false)
    public PersonnelEntity getPersonnelByWorkerId() {
        return personnelByWorkerId;
    }

    public void setPersonnelByWorkerId(PersonnelEntity personnelByWorkerId) {
        this.personnelByWorkerId = personnelByWorkerId;
    }
}
