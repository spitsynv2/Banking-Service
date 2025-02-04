package com.solvd.bankingservice.models.employee;

import java.util.Date;
import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-27
 */
public class Employee {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String jobTitle;
    private Date hireDate;
    private List<Branch> branches;
    private List<EmployeeRole> roles;

    public Employee() {}

    public Employee(Long id, String firstName, String lastName, String email, String phoneNumber, String jobTitle, Date hireDate, List<Branch> branches) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.jobTitle = jobTitle;
        this.hireDate = hireDate;
        this.branches = branches;
    }

    public Employee(Long id, String firstName, String lastName, String email, String phoneNumber, String jobTitle,
                    Date hireDate, List<Branch> branches, List<EmployeeRole> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.jobTitle = jobTitle;
        this.hireDate = hireDate;
        this.branches = branches;
        this.roles = roles;
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

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    public List<EmployeeRole> getRoles() {
        return roles;
    }

    public void setRoles(List<EmployeeRole> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", hireDate=" + hireDate +
                ", branches=" + branches +
                ", roles=" + roles +
                '}';
    }
}
