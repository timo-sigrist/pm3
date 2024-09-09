package persistence.dao;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import persistence.exceptions.DeleteFailedException;
import persistence.exceptions.SaveFailedException;
import persistence.exceptions.UpdateFailedException;


/**
 * Base class for all data access objects
 * @param <DO>
 */
public class BaseDAO<DO> {
    
    private static final Logger LOGGER = Logger.getLogger(BaseDAO.class.getName());

    @PersistenceContext
    protected final EntityManager entityManager;

    private final String className;


    /**
     * @param entityManager
     */
    public BaseDAO(EntityManager entityManager)
    {
        this.entityManager = entityManager;

        className = getClass().getName();
    }


    /**
     * Saves the given object to the Database
     * @param dataObject the object to be saved
     * @throws SaveFailedException is thrown if save failed
     * @return Dataobject with added Id
     */  
    public DO save(DO dataObject) throws SaveFailedException
    {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(dataObject);
            entityManager.getTransaction().commit();
            LOGGER.log(Level.INFO,String.format("{0} has been saved", className));
            return dataObject;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, String.format("{0} has not been saved" + e.getMessage(), className));
            entityManager.getTransaction().rollback();

            throw new SaveFailedException(e, className);
        }
    }

    /**
     * Updates a given object on the Database
     * @param dataObject the object to be updated
     * @throws UpdateFailedException is thrown if the update failed
     */
    public void update(DO dataObject) throws UpdateFailedException {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(dataObject);
            entityManager.getTransaction().commit();
            LOGGER.log(Level.INFO, String.format("{0} has been updated", className));
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, String.format("{0} has not been updated" + e.getMessage(), className));
            entityManager.getTransaction().rollback();

            throw new UpdateFailedException(e, className);
        }
        
    }

    /**
     * Deletes the given object in the Database
     * @param dataObject the object to be deleted
     * @throws DeleteFailedException is thrown if the deletion failed
     */
    public void delete(DO dataObject) throws DeleteFailedException {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.contains(dataObject) ? dataObject : entityManager.merge(dataObject));
            entityManager.getTransaction().commit();
            LOGGER.log(Level.INFO, String.format("{0} has been removed", className));
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, String.format("{0} has not been removed" + e.getMessage(), className));
            entityManager.getTransaction().rollback();

            throw new DeleteFailedException(e, className);
        }
    }
}
