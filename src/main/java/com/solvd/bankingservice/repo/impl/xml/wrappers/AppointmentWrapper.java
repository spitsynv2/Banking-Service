package com.solvd.bankingservice.repo.impl.xml.wrappers;

import com.solvd.bankingservice.models.Appointment;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
@XmlRootElement(name = "banking_service")
@XmlAccessorType(XmlAccessType.FIELD)
public class AppointmentWrapper extends AbstractWrapper<Appointment> {

    @XmlElementWrapper(name = "appointments")
    @XmlElement(name = "appointment")
    private List<Appointment> appointmentList = new ArrayList<>();

    public AppointmentWrapper() {}

    @Override
    public List<Appointment> getEntities() {
        return appointmentList;
    }

    @Override
    public void setEntities(List<Appointment> entities) {
        this.appointmentList.clear();
        this.appointmentList.addAll(entities);
    }
}
