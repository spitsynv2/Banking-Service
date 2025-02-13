package com.solvd.bankingservice.model;

import com.solvd.bankingservice.model.enums.service_request_enums.ServiceRequestStatus;
import com.solvd.bankingservice.model.enums.service_request_enums.ServiceRequestType;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-27
 */
public class ServiceRequest {
    private Long id;
    private Long employeeId;
    private Long customerId;
    private ServiceRequestType serviceRequestType;
    private ServiceRequestStatus serviceRequestStatus;
    private LocalDateTime createdAt;
    private LocalDateTime resolvedAt;
    private String notes;
    private List<Appointment> appointments;

    public ServiceRequest() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public ServiceRequestType getServiceRequestType() {
        return serviceRequestType;
    }

    public void setServiceRequestType(ServiceRequestType serviceRequestType) {
        this.serviceRequestType = serviceRequestType;
    }

    public ServiceRequestStatus getServiceRequestStatus() {
        return serviceRequestStatus;
    }

    public void setServiceRequestStatus(ServiceRequestStatus serviceRequestStatus) {
        this.serviceRequestStatus = serviceRequestStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getResolvedAt() {
        return resolvedAt;
    }

    public void setResolvedAt(LocalDateTime resolvedAt) {
        this.resolvedAt = resolvedAt;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "ServiceRequest{" +
                "id=" + id +
                ", employeeId=" + employeeId +
                ", customerId=" + customerId +
                ", serviceRequestType=" + serviceRequestType +
                ", serviceRequestStatus=" + serviceRequestStatus +
                ", createdAt=" + createdAt +
                ", resolvedAt=" + resolvedAt +
                ", notes='" + notes + '\'' +
                ", appointments=" + appointments +
                '}';
    }
}
