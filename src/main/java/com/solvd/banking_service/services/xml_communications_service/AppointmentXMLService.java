package com.solvd.banking_service.services.xml_communications_service;

import com.solvd.banking_service.models.Appointment;
import com.solvd.banking_service.services.xml_communications_service.wrappers.AbstractWrapper;
import com.solvd.banking_service.services.xml_communications_service.wrappers.AppointmentWrapper;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
public class AppointmentXMLService extends AbstractXMLService<Appointment> {

    @Override
    protected Class<? extends AbstractWrapper<Appointment>> getWrapperClass() {
        return AppointmentWrapper.class;
    }
}
