package com.j2ee.j2eetdspring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "firstname")
    @NotNull
    @NotBlank
    private String firstName;

    @Column(name = "lastname")
    @NotNull
    @NotBlank
    private String lastName;

    @Column(name = "telephone")
    @NotNull
    @NotBlank
    private String telephone;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<Adresse> addresses = new ArrayList<Adresse>();

    @Column(name = "role")
    @NotNull
    @NotBlank
    private String role;

    @Column(name = "password")
    @NotNull
    @NotBlank
    @JsonIgnore
    private String password;

    /*@ManyToMany(mappedBy = "sortie_user")
    Set<Sortie> sortie;*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<Adresse> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Adresse> addresses) {
        this.addresses = addresses;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

