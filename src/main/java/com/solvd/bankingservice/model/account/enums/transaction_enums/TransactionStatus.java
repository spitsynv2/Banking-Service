package com.solvd.bankingservice.model.account.enums.transaction_enums;

import javax.xml.bind.annotation.XmlEnum;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-28
 */
@XmlEnum
public enum TransactionStatus {
    PENDING,
    COMPLETED,
    FAILED
}
