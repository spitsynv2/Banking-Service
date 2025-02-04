package com.solvd.bankingservice.models.employee;

import java.util.Date;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-27
 */
public class Branch {
    private Long id;
    private String branchName;
    private String location;
    private String phoneNumber;
    private Date openDate;

    public Branch() {}

    public Branch(Long id, String branchName, String location, String phoneNumber, Date openDate) {
        this.id = id;
        this.branchName = branchName;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.openDate = openDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "id=" + id +
                ", branchName='" + branchName + '\'' +
                ", location='" + location + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", openDate=" + openDate +
                '}';
    }
}
