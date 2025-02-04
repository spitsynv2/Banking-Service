package com.solvd.bankingservice.daos.impl.xml.jaxb;

import com.solvd.bankingservice.models.Appointment;
import com.solvd.bankingservice.daos.impl.xml.jaxb.wrappers.AbstractWrapper;
import com.solvd.bankingservice.daos.impl.xml.jaxb.wrappers.AppointmentWrapper;

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
