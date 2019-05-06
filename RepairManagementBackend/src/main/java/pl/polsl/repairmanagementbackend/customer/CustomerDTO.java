package pl.polsl.repairmanagementbackend.customer;

import pl.polsl.repairmanagementbackend.DTO;
import pl.polsl.repairmanagementbackend.address.AddressDTO;


public class CustomerDTO implements DTO {


    private Integer id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private AddressDTO address;

    public CustomerDTO(Integer id, String firstName, String lastName, String phoneNumber, AddressDTO address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
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

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }


    @Override
    public CustomerEntity toEntity(){
        return new CustomerEntity(firstName, lastName, phoneNumber, address.toEntity());
    }
}
