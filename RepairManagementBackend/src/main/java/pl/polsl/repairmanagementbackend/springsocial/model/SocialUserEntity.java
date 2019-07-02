package pl.polsl.repairmanagementbackend.springsocial.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import pl.polsl.repairmanagementbackend.customer.CustomerEntity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity(name = "social_user")
@Table(name = "social_user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class SocialUserEntity {

    private Long id;
    private String name;
    private String email;

    private Boolean emailVerified = false;

    private AuthProvider provider;
    private String providerId;
    private CustomerEntity customer;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Column
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Email
    @Column(nullable = false)
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


    @Column(nullable = false)
    public Boolean getEmailVerified() {
        return emailVerified;
    }
    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }


    @NotNull
    @Enumerated(EnumType.STRING)
    public AuthProvider getProvider() {
        return provider;
    }
    public void setProvider(AuthProvider provider) {
        this.provider = provider;
    }

    public String getProviderId() {
        return providerId;
    }
    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @JsonSerialize(using = ToStringSerializer.class)
    public CustomerEntity getCustomer() {
        return customer;
    }
    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }
}