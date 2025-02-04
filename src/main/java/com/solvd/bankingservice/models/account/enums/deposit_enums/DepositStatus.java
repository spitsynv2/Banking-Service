package com.solvd.bankingservice.models.account.enums.deposit_enums;

import javax.xml.bind.annotation.XmlEnum;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-28
 */
@XmlEnum
public enum DepositStatus {
    ACTIVE,
    CLOSED,
    WITHDRAWN
}
