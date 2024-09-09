package app.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Manages the entities used to persist and get data from the database
 */
public class PM3EntityManager {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY;

    static {
        ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("PM3PU");
    }

    /**
     * @return entityManager
     */
    public static EntityManager getEntityManager() {
        return ENTITY_MANAGER_FACTORY.createEntityManager();
    }

    /**
     * Closes entityManager
     */
    public static void close() {
        ENTITY_MANAGER_FACTORY.close();
    }
}
