package service;

import entity.Model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

/**
 * Represents model service
 * @author Marta Polcyn
 */

public class ModelService {

    protected EntityManager entityManager;

    public ModelService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * This method adds a new yacht model to models table
     */
    public void addModel() {

        entityManager.getTransaction().begin();

        Model model = new Model();
        model.setModelDescription("Antila 33");
        model.setPricePerDay(BigDecimal.valueOf(1000));
        model.setPricePerWeek(BigDecimal.valueOf(5000));
        model.setBunkCount(10);

        entityManager.persist(model);
        entityManager.getTransaction().commit();

    }

    public Model findModel(Long id) {

        Model model = (Model) entityManager
                .createQuery("SELECT * FROM TABLE models WHERE model.model_desc = :model_desc")
                .setParameter("model_desc", "KEY1")
                .getSingleResult();


        return entityManager.find(Model.class, id);
    }
}
