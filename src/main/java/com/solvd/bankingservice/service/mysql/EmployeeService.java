package com.solvd.bankingservice.service.mysql;

import com.solvd.bankingservice.repo.*;
import com.solvd.bankingservice.repo.impl.mysql.BranchMySQLJdbcImpl;
import com.solvd.bankingservice.repo.impl.mysql.EmployeeMySQLJdbcImpl;
import com.solvd.bankingservice.repo.impl.mysql.EmployeeRoleMySQLJdbcImpl;
import com.solvd.bankingservice.model.employee.Employee;
import com.solvd.bankingservice.service.AbstractService;
import com.solvd.bankingservice.service.IEmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
public class EmployeeService extends AbstractService<Employee,Long> implements IEmployeeService {

    private final IEmployeeDAO employeeDAO = new EmployeeMySQLJdbcImpl();
    private final IBranchDAO branchDAO = new BranchMySQLJdbcImpl();
    private final IEmployeeRoleDAO employeeRoleDAO = new EmployeeRoleMySQLJdbcImpl();

    private static final Logger log = LogManager.getLogger(EmployeeService.class);

    @Override
    public Employee getAllEmployeeDataById(Long employeeId) {
        Employee employee = employeeDAO.readById(employeeId);

        if (employee == null) {
            log.error("Cannot find Employee with ID {}", employeeId);
            return null;
        }

        employee.setBranches(branchDAO.readAllByForeignKeyId(employeeId));
        employee.setRoles(employeeRoleDAO.readAllByForeignKeyId(employeeId));

        return employee;
    }

    @Override
    public Employee readById(Long aLong) {
        return getAllEmployeeDataById(aLong);
    }

    @Override
    protected IDAO<Employee, Long> getDao() {
        return employeeDAO;
    }
}
