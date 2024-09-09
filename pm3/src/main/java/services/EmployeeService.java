package services;

import java.util.List;

import javax.persistence.EntityManager;

import persistence.EmployeeDO;
import persistence.UserDO;
import persistence.dao.EmployeeDAO;
import persistence.exceptions.DeleteFailedException;
import persistence.exceptions.SaveFailedException;
import persistence.exceptions.UpdateFailedException;

/**
 * Service for all actions regarding an employee
 */
public class EmployeeService {
    private final EmployeeDAO employeeDAO;

    /**
     * @param entityManager
     */
    public EmployeeService(EntityManager entityManager)
    {
        employeeDAO = new EmployeeDAO(entityManager);
    }

    /**
     * Find all users for a given employer
     * @param employer the employer to search for
     * @return list of all employees belonging to the employer
     */
    public List<EmployeeDO> findByUser(UserDO employer)
    {
        return employeeDAO.findByUser(employer);
    }


    /**
     * Create a new Employee 
     * @param employee employee to create
     * @throws SaveFailedException is thrown if the save failed
     */
    public void createNewEmployee(EmployeeDO employee) throws SaveFailedException
    {
        employeeDAO.save(employee);
    }

    /**
     * Update a given employee with new detail
     * @param employee the employee to update
     * @throws UpdateFailedException is thrown if the update has failed
     */
    public void updateEmployee(EmployeeDO employee) throws UpdateFailedException
    {
        employeeDAO.update(employee);
    }

    /**
     * Deletes a given employee
     * @param employee
     * @throws DeleteFailedException
     */
    public void deleteEmployee(EmployeeDO employee) throws DeleteFailedException
    {
        // TODO: Macht keinen Sinn aus der Business Perspektive
        // Alte Mitarbeiter sollten nicht gelöscht werden -> isActive oder so etwas hinzufügen
        employeeDAO.delete(employee);
    }

}
