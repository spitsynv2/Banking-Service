package com.solvd.models.employee;

/**
 * @author Vadym Spitsyn
 * @created 2025-01-27
 */
public class EmployeeRole {
    private Long id;
    private String roleName;
    private String responsibilities;

    public EmployeeRole() {
    }

    public EmployeeRole(Long id, String roleName, String responsibilities) {
        this.id = id;
        this.roleName = roleName;
        this.responsibilities = responsibilities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }
}
