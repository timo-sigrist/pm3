package common;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class H2Database {
    private  final EntityManagerFactory ENTITY_MANAGER_FACTORY;

    public H2Database() {
        ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("test");
    }

    public EntityManager getEntityManager() {
        return ENTITY_MANAGER_FACTORY.createEntityManager();
    }

    public void close() {
        ENTITY_MANAGER_FACTORY.close();
    }


    public<DO> DO save(DO dataObject)
    {
        final EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(dataObject);
        entityManager.getTransaction().commit();
        return dataObject;
    }
    

    public<DO> DO update(DO dataObject)
    {
        final EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(dataObject);
        entityManager.getTransaction().commit();

        return dataObject;
    }


    public<DO> void delete(DO dataObject)
    {
        final EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(dataObject);
        entityManager.getTransaction().commit();
    }
}
