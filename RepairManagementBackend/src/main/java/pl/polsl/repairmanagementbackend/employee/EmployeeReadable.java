package pl.polsl.repairmanagementbackend.employee;

import pl.polsl.repairmanagementbackend.address.AddressDTO;

public class EmployeeReadable {
    private Integer id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String role;
    private String username;
    private AddressDTO address;


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

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public AddressDTO getAddress() {
        return address;
    }
    public void setAddress(AddressDTO address) {
        this.address = address;
    }

}
