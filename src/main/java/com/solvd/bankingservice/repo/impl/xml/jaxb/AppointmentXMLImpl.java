package com.solvd.bankingservice.repo.impl.xml.jaxb;

import com.solvd.bankingservice.models.Appointment;
import com.solvd.bankingservice.repo.impl.xml.wrappers.AbstractWrapper;
import com.solvd.bankingservice.repo.impl.xml.wrappers.AppointmentWrapper;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
public class AppointmentXMLImpl extends AbstractXMLImpl<Appointment> {

    @Override
    protected Class<? extends AbstractWrapper<Appointment>> getWrapperClass() {
        return AppointmentWrapper.class;
    }
}
