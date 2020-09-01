package service;

import entity.Yacht;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Represents yacht service
 * @author Marta Polcyn
 */

public class YachtService {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("yachts");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    /**
     * This method adds a new yacht item to yachts table
     * In case the model of the yacht is unknown, it also adds a new model to models table
     */
    public void addYacht() {

            entityManager.getTransaction().begin();

            Yacht yacht = new Yacht();
            yacht.setModelId(3L);

            entityManager.persist(yacht);
            entityManager.getTransaction().commit();

    }


    public Yacht findYacht(Long id) {
        return entityManager.find(Yacht.class, id);
    }
}
