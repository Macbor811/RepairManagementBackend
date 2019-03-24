package pl.polsl.repairmanagementbackend.address;

import pl.polsl.repairmanagementbackend.client.ClientEntity;
import pl.polsl.repairmanagementbackend.personnel.PersonnelEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity(name = "address")
@Table(name = "address", schema = "public", catalog = "postgres")
public class AddressEntity {
    private Integer id;
    private String postCode;
    private String city;
    private String street;
    private Integer number;
    private Collection<ClientEntity> clientsById;
    private Collection<PersonnelEntity> personnelsById;

    public AddressEntity(String postCode, String city, String street, Integer number, Collection<ClientEntity> clientsById, Collection<PersonnelEntity> personnelsById) {
        this.postCode = postCode;
        this.city = city;
        this.street = street;
        this.number = number;
        this.clientsById = clientsById;
        this.personnelsById = personnelsById;
    }

    public AddressEntity(){

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
    @Column(name = "post_code", nullable = true, length = 7)
    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    @Basic
    @Column(name = "city", nullable = true, length = 50)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "street", nullable = true, length = 50)
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Basic
    @Column(name = "number", nullable = true)
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
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

    @OneToMany(mappedBy = "addressByAddressId")
    public Collection<ClientEntity> getClientsById() {
        return clientsById;
    }

    public void setClientsById(Collection<ClientEntity> clientsById) {
        this.clientsById = clientsById;
    }

    @OneToMany(mappedBy = "addressByAddressId")
    public Collection<PersonnelEntity> getPersonnelsById() {
        return personnelsById;
    }

    public void setPersonnelsById(Collection<PersonnelEntity> personnelsById) {
        this.personnelsById = personnelsById;
    }

    public AddressDTO toDTO(){
        return new AddressDTO(getId(), getPostCode(), getCity(), getStreet(), getNumber());
    }
}
