package com.solvd.banking_service.models;

import com.solvd.banking_service.models.enums.AppointmentStatus;
import com.solvd.banking_service.utils.LocalDateTimeAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-27
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Appointment {

    @XmlElement
    private Long id;

    @XmlElement
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime appointmentDate;

    @XmlElement
    private AppointmentStatus appointmentStatus;

    @XmlElement
    private String notes;

    public Appointment() {}

    public Appointment(Long id, LocalDateTime appointmentDate, AppointmentStatus appointmentStatus, String notes) {
        this.id = id;
        this.appointmentDate = appointmentDate;
        this.appointmentStatus = appointmentStatus;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }
    public void setAppointmentDate(LocalDateTime appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public AppointmentStatus getAppointmentStatus() {
        return appointmentStatus;
    }
    public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", appointmentDate=" + appointmentDate +
                ", appointmentStatus=" + appointmentStatus +
                ", notes='" + notes + '\'' +
                '}';
    }
}
