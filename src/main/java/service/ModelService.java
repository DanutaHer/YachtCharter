package service;

import entity.Model;
import entity.Yacht;
import util.PersistenceManager;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents model service
 *
 * @author Marta Polcyn
 */

public class ModelService {

    EntityManager em = PersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();

    /**
     * This method adds a new yacht model to models table
     */
    public void addModel(String description) {

        try {

            em.getTransaction().begin();
            Model model = new Model();
            model.setModelDescription(description);

            Scanner sc = new Scanner(System.in);
            System.out.println("Price per day?");

            model.setPricePerDay(BigDecimal.valueOf(Integer.parseInt(sc.nextLine())));
            System.out.println("Price per week?");
            model.setPricePerWeek(BigDecimal.valueOf(Integer.parseInt(sc.nextLine())));

            System.out.println("How many places to sleep?");
            model.setBunkCount(Integer.parseInt(sc.nextLine()));

            em.persist(model);
            em.getTransaction().commit();

        } catch (Exception ex) {
            ex.printStackTrace();
//            em.getTransaction().rollback();
        } finally {
            em.close();
        }

    }

    public List<Model> findModel(String name) {
        EntityTransaction et = null;
        List<Model> models = new ArrayList<>();
        try {
            models =  em.createQuery(
                    "SELECT m FROM models AS m WHERE model_desc LIKE :model_desc")
                    .setParameter("model_desc", name.toLowerCase())
                    .getResultList();
        } catch (Exception e) {
            if (et != null) {
                et.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
        return models;
    }


    public Model findModel(Long id) {
        return em.find(Model.class, id);
    }
}
