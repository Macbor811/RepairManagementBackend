package pl.polsl.repairmanagementbackend.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pl.polsl.repairmanagementbackend.DTO;
import pl.polsl.repairmanagementbackend.activity.ActivityDTO;
import pl.polsl.repairmanagementbackend.address.AddressDTO;
import pl.polsl.repairmanagementbackend.request.RequestDTO;

import java.util.Collection;

public class EmployeeDTO implements DTO {


    private Integer id;
    private String firstName;

    private String lastName;
    private String phoneNumber;
    private String role;
    private String username;
    private String password;
    @JsonIgnore
    private Collection<ActivityDTO> activities;
    private AddressDTO address;
    @JsonIgnore
    private Collection<RequestDTO> requests;

    public EmployeeDTO(Integer id,
                       String firstName,
                       String lastName,
                       String phoneNumber,
                       String role,
                       String username,
                       String password,
                      // Collection<ActivityDTO> activities,
                       AddressDTO address
                      // Collection<RequestDTO> requests
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.username = username;
        this.password = password;
        //this.activities = activities;
        this.address = address;
       // this.requests = requests;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<ActivityDTO> getActivities() {
        return activities;
    }

    public void setActivities(Collection<ActivityDTO> activities) {
        this.activities = activities;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public Collection<RequestDTO> getRequests() {
        return requests;
    }

    public void setRequests(Collection<RequestDTO> requests) {
        this.requests = requests;
    }

    @Override
    public EmployeeEntity toEntity(){
        return new EmployeeEntity(firstName, lastName, phoneNumber, role, username, password, address.toEntity());
             //   activities == null ? null : activities.stream().map(ActivityDTO::toEntity).collect(Collectors.toList()),

              //  requests == null ? null :requests.stream().map(RequestDTO::toEntity).collect(Collectors.toList()));
    }
}
