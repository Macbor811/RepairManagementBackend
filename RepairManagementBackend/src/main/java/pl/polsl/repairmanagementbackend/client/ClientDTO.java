package pl.polsl.repairmanagementbackend.client;

import pl.polsl.repairmanagementbackend.address.AddressDTO;
import pl.polsl.repairmanagementbackend.item.ItemDTO;

import java.util.Collection;

public class ClientDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private AddressDTO addressByAddressId;
    private Collection<ItemDTO> itemsById;

    public ClientDTO(Integer id, String firstName, String lastName, String phoneNumber, AddressDTO addressByAddressId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.addressByAddressId = addressByAddressId;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public AddressDTO getAddressByAddressId() {
        return addressByAddressId;
    }

    public void setAddressByAddressId(AddressDTO addressByAddressId) {
        this.addressByAddressId = addressByAddressId;
    }

    public Collection<ItemDTO> getItemsById() {
        return itemsById;
    }

    public void setItemsById(Collection<ItemDTO> itemsById) {
        this.itemsById = itemsById;
    }

    public ClientEntity toEntity(){
        return new ClientEntity(firstName, lastName, phoneNumber, addressByAddressId.toEntity(), null);
    }
}
