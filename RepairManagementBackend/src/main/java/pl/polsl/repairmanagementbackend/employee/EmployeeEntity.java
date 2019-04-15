package pl.polsl.repairmanagementbackend.employee;

import pl.polsl.repairmanagementbackend.activity.ActivityEntity;
import pl.polsl.repairmanagementbackend.address.AddressEntity;
import pl.polsl.repairmanagementbackend.request.RequestEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "employee", schema = "public", catalog = "postgres")
public class EmployeeEntity implements pl.polsl.repairmanagementbackend.Entity {
    private Integer id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String role;
    private String username;
    private String password;
    private Collection<ActivityEntity> activities;
    private AddressEntity address;
    private Collection<RequestEntity> requests;

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "first_name", nullable = true, length = 50)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = true, length = 50)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "phone_number", nullable = true, length = 10)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "role", nullable = true, length = 10)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Basic
    @Column(name = "username", nullable = true, length = 50)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 50)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeEntity that = (EmployeeEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(role, that.role) &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, phoneNumber, role, username, password);
    }

    @OneToMany(mappedBy = "worker")
    public Collection<ActivityEntity> getActivities() {
        return activities;
    }

    public void setActivities(Collection<ActivityEntity> activities) {
        this.activities = activities;
    }

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    @OneToMany(mappedBy = "manager")
    public Collection<RequestEntity> getRequests() {
        return requests;
    }

    public void setRequests(Collection<RequestEntity> requests) {
        this.requests = requests;
    }

    @Override
    public EmployeeDTO toDTO(){
        return new EmployeeDTO(id, firstName, lastName, phoneNumber, role,
                username, password, null, address.toDTO(), null);
    }
}