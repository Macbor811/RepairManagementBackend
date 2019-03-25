package pl.polsl.repairmanagementbackend.client;

import pl.polsl.repairmanagementbackend.address.AddressEntity;
import pl.polsl.repairmanagementbackend.item.ItemEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity(name = "client")
@Table(name = "client", schema = "public", catalog = "postgres")
public class ClientEntity {
    private Integer id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private AddressEntity address;
    private Collection<ItemEntity> items;

    public ClientEntity(){}
    public ClientEntity(String firstName, String lastName, String phoneNumber, AddressEntity address, Collection<ItemEntity> items) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.items = items;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientEntity that = (ClientEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, phoneNumber);
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    @OneToMany(mappedBy = "owner")
    public Collection<ItemEntity> getItems() {
        return items;
    }

    public void setItems(Collection<ItemEntity> items) {
        this.items = items;
    }

    public ClientDTO toDTO(){
        return new ClientDTO(getId(), getFirstName(), getLastName(), getPhoneNumber(), getAddress().toDTO());
    }
}
