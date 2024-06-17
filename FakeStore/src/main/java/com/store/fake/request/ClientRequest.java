package com.store.fake.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ClientRequest {

    private Long idClient;
    @NotNull(message = "Email cannot be null")
    private String email;
    @NotNull(message = "Username cannot be nuall")
    private String username;
    private String password;
    @NotNull(message = "Firstname cannot be null")
    private String firstname;
    @NotNull(message = "Lastname cannot be null")
    private String lastname;
    private String phone;


    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public @NotNull(message = "Email cannot be null") String getEmail() {
        return email;
    }

    public void setEmail(@NotNull(message = "Email cannot be null") String email) {
        this.email = email;
    }

    public @NotNull(message = "Username cannot be null") String getUsername() {
        return username;
    }

    public void setUsername(@NotNull(message = "Username cannot be null") String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public @NotNull(message = "Firstname cannot be null") String getFirstname() {
        return firstname;
    }

    public void setFirstname(@NotNull(message = "Firstname cannot be null") String firstname) {
        this.firstname = firstname;
    }

    public @NotNull(message = "Lastname cannot be null") String getLastname() {
        return lastname;
    }

    public void setLastname(@NotNull(message = "Lastname cannot be null") String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
