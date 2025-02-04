package com.solvd.bankingservice.services.mysql;

import com.solvd.bankingservice.daos.*;
import com.solvd.bankingservice.daos.impl.mysql.BranchDAOImpl;
import com.solvd.bankingservice.daos.impl.mysql.EmployeeDAOImpl;
import com.solvd.bankingservice.daos.impl.mysql.EmployeeRoleDAOImpl;
import com.solvd.bankingservice.models.employee.Employee;
import com.solvd.bankingservice.services.AbstractService;
import com.solvd.bankingservice.services.IEmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
public class EmployeeService extends AbstractService<Employee,Long> implements IEmployeeService {

    private final IEmployeeDAO employeeDAO = new EmployeeDAOImpl();
    private final IBranchDAO branchDAO = new BranchDAOImpl();
    private final IEmployeeRoleDAO employeeRoleDAO = new EmployeeRoleDAOImpl();

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
