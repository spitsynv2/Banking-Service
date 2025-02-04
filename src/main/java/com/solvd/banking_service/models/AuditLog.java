package com.solvd.banking_service.models;

import com.solvd.banking_service.models.employee.Employee;
import com.solvd.banking_service.models.enums.LogActionType;

import java.time.LocalDateTime;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-27
 */
public class AuditLog {
    private Long logId;
    private Long auditorId;
    private Long customerId;
    private LogActionType logActionType;
    private String actionDescription;
    private LocalDateTime date;

    public AuditLog() {}

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public Long getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(Long auditorId) {
        this.auditorId = auditorId;
    }

    public LogActionType getLogActionType() {
        return logActionType;
    }

    public void setLogActionType(LogActionType logActionType) {
        this.logActionType = logActionType;
    }

    public String getActionDescription() {
        return actionDescription;
    }

    public void setActionDescription(String actionDescription) {
        this.actionDescription = actionDescription;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "AuditLog{" +
                "logId=" + logId +
                ", auditorId=" + auditorId +
                ", customerId=" + customerId +
                ", logActionType=" + logActionType +
                ", actionDescription='" + actionDescription + '\'' +
                ", date=" + date +
                '}';
    }
}
