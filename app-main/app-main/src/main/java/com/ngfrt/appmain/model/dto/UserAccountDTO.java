package com.ngfrt.appmain.model.dto;

import com.ngfrt.appmain.model.validations.UniqueUserEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserAccountDTO {

    @NotBlank(message = "Please provide a First Name")
    @Size(min = 2, max = 25, message = "First name must be between 2 and 25 characters")
    private String firstName;

    @NotBlank(message = "Please provide a First Name")
    @Size(min = 2, max = 25, message = "Last name must be between 2 and 25 characters")
    private String lastName;

    @NotBlank(message = "Please provide an email")
    @Email(message = "Must be a valid email address format")
    private String email;

    @NotBlank(message = "Phone is required")
    @Size(min = 5, max = 20, message = "Contact phone must be between 5 and 20 characters")
    private String phone;

    public String getFirstName() {
        return firstName;
    }

    public UserAccountDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserAccountDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserAccountDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserAccountDTO setPhone(String phone) {
        this.phone = phone;
        return this;
    }
}
