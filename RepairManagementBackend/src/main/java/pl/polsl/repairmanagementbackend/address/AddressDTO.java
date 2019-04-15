package pl.polsl.repairmanagementbackend.address;


import pl.polsl.repairmanagementbackend.DTO;

public class AddressDTO implements DTO {

    private Integer id;
    private String postCode;
    private String city;
    private String street;
    private String number;


    public AddressDTO(Integer id, String postCode, String city, String street, String number) {
        this.id = id;
        this.postCode = postCode;
        this.city = city;
        this.street = street;
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public AddressEntity toEntity(){
        return new AddressEntity(getPostCode(), getCity(), getStreet(), getNumber(), null, null);
    }
}
