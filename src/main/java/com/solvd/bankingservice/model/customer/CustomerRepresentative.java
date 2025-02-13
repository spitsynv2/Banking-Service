package com.solvd.bankingservice.model.customer;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-27
 */
public class CustomerRepresentative {
    private Long id;
    private String firstName;
    private String lastName;
    private String position;
    private String email;
    private String phoneNumber;
    private Boolean isPrimaryContact;

    public CustomerRepresentative() {}

    public CustomerRepresentative(Long id, String firstName, String lastName, String position, String email, String phoneNumber, Boolean isPrimaryContact) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.isPrimaryContact = isPrimaryContact;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean isPrimaryContact() {
        return isPrimaryContact;
    }

    public void setPrimaryContact(Boolean primaryContact) {
        isPrimaryContact = primaryContact;
    }

    @Override
    public String toString() {
        return "CustomerRepresentative{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", position='" + position + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", isPrimaryContact=" + isPrimaryContact +
                '}';
    }
}
