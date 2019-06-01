package pl.polsl.repairmanagementbackend.activitytype;


import pl.polsl.repairmanagementbackend.activity.ActivityEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "activity_type", schema = "public", catalog = "postgres")
public class ActivityTypeEntity{
    private Integer id;
    private String type;
    private Collection<ActivityEntity> activities;


    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type", nullable = false, length = 50)
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    @OneToMany(mappedBy = "activityType")
    public Collection<ActivityEntity> getActivities() {
        return activities;
    }
    public void setActivities(Collection<ActivityEntity> activities) {
        this.activities = activities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityTypeEntity that = (ActivityTypeEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }
}
