package app.controller;

import persistence.UserDO;

import javax.persistence.EntityManager;

/**
 * Base class of all controllers
 */
public class BaseController {
    protected static UserDO loggedInUser;
    private final EntityManager entityManager = PM3EntityManager.getEntityManager();

    /**
     * @return entityManager
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
