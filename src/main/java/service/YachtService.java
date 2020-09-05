package service;

import entity.Yacht;
import util.PersistenceManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Represents yacht service
 *
 * @author Marta Polcyn
 */

public class YachtService {

    EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
    EntityManager em = emf.createEntityManager();

    /**
     * This method adds a new yacht item to yachts table
     * In case the model of the yacht is unknown, it also adds a new model to models table
     */
    public void addYacht() {

        try {
            em.getTransaction().begin();
            Yacht yacht = new Yacht();
            yacht.setModelId(3L);
            em.persist(yacht);
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }


    public Yacht findYacht(Long id) {
        return em.find(Yacht.class, id);
    }
}
