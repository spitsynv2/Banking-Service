package com.solvd.banking_service.models;

import com.solvd.banking_service.models.enums.service_request_enums.ServiceRequestStatus;
import com.solvd.banking_service.models.enums.service_request_enums.ServiceRequestType;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-27
 */
public class ServiceRequest {
    private Long id;
    private Long employeeId;
    private ServiceRequestType serviceRequestType;
    private ServiceRequestStatus serviceRequestStatus;
    private LocalDateTime createdAt;
    private LocalDateTime resolvedAt;
    private String notes;
    private List<Appointment> appointments;

    public ServiceRequest() {
    }

    public ServiceRequest(Long id, Long employeeId, ServiceRequestType serviceRequestType,
                          ServiceRequestStatus serviceRequestStatus, LocalDateTime createdAt, LocalDateTime resolvedAt, String notes) {
        this.id = id;
        this.employeeId = employeeId;
        this.serviceRequestType = serviceRequestType;
        this.serviceRequestStatus = serviceRequestStatus;
        this.createdAt = createdAt;
        this.resolvedAt = resolvedAt;
        this.notes = notes;
    }

    public ServiceRequest(Long id, Long employeeId, ServiceRequestType serviceRequestType,
                          ServiceRequestStatus serviceRequestStatus, LocalDateTime createdAt, LocalDateTime resolvedAt, String notes, List<Appointment> appointments) {
        this.id = id;
        this.employeeId = employeeId;
        this.serviceRequestType = serviceRequestType;
        this.serviceRequestStatus = serviceRequestStatus;
        this.createdAt = createdAt;
        this.resolvedAt = resolvedAt;
        this.notes = notes;
        this.appointments = appointments;
    }

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
}
