package com.solvd.models;

import com.solvd.models.employee.Employee;
import com.solvd.models.enums.LogActionType;

import java.time.LocalDateTime;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-27
 */
public class AuditLog {
    private Long logId;
    private Employee auditor;
    private LogActionType logActionType;
    private String actionDescription;
    private LocalDateTime date;

    public AuditLog() {
    }

    public AuditLog(Long logId, Employee auditor, LogActionType logActionType, String actionDescription, LocalDateTime date) {
        this.logId = logId;
        this.auditor = auditor;
        this.logActionType = logActionType;
        this.actionDescription = actionDescription;
        this.date = date;
    }

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public Employee getAuditor() {
        return auditor;
    }

    public void setAuditor(Employee auditor) {
        this.auditor = auditor;
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
}
