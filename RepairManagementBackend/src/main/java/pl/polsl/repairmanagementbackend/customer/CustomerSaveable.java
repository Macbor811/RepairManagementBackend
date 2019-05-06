package pl.polsl.repairmanagementbackend.customer;

import pl.polsl.repairmanagementbackend.address.AddressDTO;

public class CustomerSaveable {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private AddressDTO address;



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
}
