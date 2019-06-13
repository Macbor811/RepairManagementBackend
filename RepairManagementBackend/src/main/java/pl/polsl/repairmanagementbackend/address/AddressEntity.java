package pl.polsl.repairmanagementbackend.address;

import pl.polsl.repairmanagementbackend.customer.CustomerEntity;
import pl.polsl.repairmanagementbackend.employee.EmployeeEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity(name = "address")
@Table(name = "address")
public class AddressEntity{
    private Integer id;
    private String postCode;
    private String city;
    private String street;
    private String number;
    private Collection<CustomerEntity> customers;
    private Collection<EmployeeEntity> employees;

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "post_code", nullable = false, length = 7)
    public String getPostCode() {
        return postCode;
    }
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    @Basic
    @Column(name = "city", nullable = false, length = 50)
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "street", nullable = false, length = 50)
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Basic
    @Column(name = "number", nullable = false, length = 7)
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressEntity that = (AddressEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(postCode, that.postCode) &&
                Objects.equals(city, that.city) &&
                Objects.equals(street, that.street) &&
                Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, postCode, city, street, number);
    }

    @OneToMany(mappedBy = "address")
    public Collection<CustomerEntity> getCustomers() {
        return customers;
    }
    public void setCustomers(Collection<CustomerEntity> customers) {
        this.customers = customers;
    }

    @OneToMany(mappedBy = "address")
    public Collection<EmployeeEntity> getEmployees() {
        return employees;
    }
    public void setEmployees(Collection<EmployeeEntity> employees) {
        this.employees = employees;
    }

}
