package com.solvd.bankingservice.model.account.enums;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-28
 */
@XmlEnum
public enum CardType {
    @XmlEnumValue("DEBIT_MASTERCARD")
    DEBIT_MASTERCARD,

    CREDIT_MASTERCARD,

    DEBIT_VISA,

    CREDIT_VISA
}
