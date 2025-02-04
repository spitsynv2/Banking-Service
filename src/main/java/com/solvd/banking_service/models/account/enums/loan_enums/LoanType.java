package com.solvd.banking_service.models.account.enums.loan_enums;

import javax.xml.bind.annotation.XmlEnum;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-28
 */
@XmlEnum
public enum LoanType {
    PERSONAL,
    BUSINESS,
    MORTGAGE
}
