package services;

import javax.persistence.EntityManager;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import persistence.*;
import persistence.dao.PerformanceRatingDAO;
import persistence.exceptions.SaveFailedException;
import persistence.dao.EmployeeDAO;
import persistence.exceptions.UpdateFailedException;

/**
 * Service for all actions regarding a performance rating
 */
public class PerformanceRatingService {
    private final PerformanceRatingDAO performanceRatingDAO;
    private final EmployeeDAO employeeDAO;
    private final KeyService keyService;

    /**
     * @param entityManager
     */
    public PerformanceRatingService(EntityManager entityManager) {
        performanceRatingDAO = new PerformanceRatingDAO(entityManager);
        employeeDAO = new EmployeeDAO(entityManager);
        keyService = new KeyService();
    }

    /**
     * Find all performance ratings belonging to a given employer
     * @param employer the employer for which ratings should be searched for
     * @return all ratings assigned to the given employer
     */
    public List<PerformanceRatingDO> findByEmployer(UserDO employer) {
        return performanceRatingDAO.findByEmployer(employer);
    }

    /**
     * Find all performance ratings belonging to a given employee
     * @param employee the employee for which rating should be searched
     * @return all ratings assigned to the given employee
     */
    public List<PerformanceRatingDO> findByEmployee(EmployeeDO employee)
    {
        return performanceRatingDAO.findByEmployee(employee);
    }


    /**
     * Creates a new Performance Rating for an employer and an employee
     * @param employer  the given employer
     * @param employeeId the employee
     */
    public PerformanceRatingDO createNew(UserDO employer, int employeeId) throws SaveFailedException
    {
        // Get the EmplyoeeDO for a given employeeId
        EmployeeDO employee = employeeDAO.findByID(employeeId);

        PerformanceRatingDO rating = new PerformanceRatingDO();
        rating.setEmployeeDo(employee);
        rating.setUserDo(employer);

        return performanceRatingDAO.save(rating);
    }

    /**
     * This Method sets the performancerating to the next step
     * @return boolean if successful
     */
    public boolean updateStatus(PerformanceRatingDO performanceRatingDO, ActiveStep nextStep) {
        boolean sucessStatus = false;
        switch (nextStep) {
            case STEP_TWO -> sucessStatus = updateStepOne(performanceRatingDO);

        }
        return sucessStatus;
    }

    private boolean updateStepOne(PerformanceRatingDO performanceRatingDO) {
        boolean updateStatus = false;
        String key = keyService.createKey();
        performanceRatingDO.setEmployeSecretkey(key);
        performanceRatingDO.setStatus("ONE");
        try {
            performanceRatingDAO.update(performanceRatingDO);
            updateStatus = true;
        } catch (UpdateFailedException e) {
            e.printStackTrace();
        }
        return updateStatus;
    }

}
