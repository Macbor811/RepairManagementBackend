package pl.polsl.repairmanagementbackend.request;

import pl.polsl.repairmanagementbackend.DTO;
import pl.polsl.repairmanagementbackend.activity.ActivityEntity;
import pl.polsl.repairmanagementbackend.item.ItemEntity;
import pl.polsl.repairmanagementbackend.employee.EmployeeEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity(name = "request")
@Table(name = "request", schema = "public", catalog = "postgres")
public class RequestEntity implements pl.polsl.repairmanagementbackend.Entity {
    private Integer id;
    private String description;
    private String result;
    private String status;
    private Timestamp registerDate;
    private Timestamp endDate;
    private Collection<ActivityEntity> activities;
    private ItemEntity item;
    private EmployeeEntity manager;

    public RequestEntity(String description, String result, String status, Timestamp registerDate, Timestamp endDate, Collection<ActivityEntity> activities, ItemEntity item, EmployeeEntity manager) {
        this.description = description;
        this.result = result;
        this.status = status;
        this.registerDate = registerDate;
        this.endDate = endDate;
        this.activities = activities;
        this.item = item;
        this.manager = manager;
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
        RequestEntity that = (RequestEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(description, that.description) &&
                Objects.equals(result, that.result) &&
                Objects.equals(status, that.status) &&
                Objects.equals(registerDate, that.registerDate) &&
                Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, result, status, registerDate, endDate);
    }

    @OneToMany(mappedBy = "request")
    public Collection<ActivityEntity> getActivities() {
        return activities;
    }

    public void setActivities(Collection<ActivityEntity> activities) {
        this.activities = activities;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id", referencedColumnName = "id", nullable = false)
    public ItemEntity getItem() {
        return item;
    }

    public void setItem(ItemEntity item) {
        this.item = item;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manager_id", referencedColumnName = "id", nullable = false)
    public EmployeeEntity getManager() {
        return manager;
    }

    public void setManager(EmployeeEntity manager) {
        this.manager = manager;
    }

    @Override
    public RequestDTO toDTO() {
        return new RequestDTO(id, description, result, status, registerDate, endDate,
               activities == null ? null : activities.stream().map(ActivityEntity::toDTO).collect(Collectors.toList()), item.toDTO(), manager.toDTO());
    }
}
