package service;

import entity.Model;
import entity.Yacht;
import util.PersistenceManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents yacht service
 *
 * @author Marta Polcyn
 */

public class YachtService {

    EntityManager em = PersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();

    /**
     * This method adds a new yacht item to yachts table
     * In case the model of the yacht is unknown, it also adds a new model to models table
     */
    public void addYacht() {

        try {

            Scanner sc = new Scanner(System.in);
            em.getTransaction().begin();
            Yacht yacht = new Yacht();
            // what model is this new yacht?
            System.out.println("What model did you buy?");
            String name = sc.nextLine();

            // find model by model name
            ModelService modelService = new ModelService();

            Model model = modelService.findModel(name).get(0);

            yacht.setModelId(model.getModelId());
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

    public List<Yacht> findYacht(String name) {

        ModelService modelService = new ModelService();

        List<Yacht> yachts = new ArrayList<>();
        List<Model> models = new ArrayList<>();

        models = modelService.findModel(name);

        Long model_id = models.get(0).getModelId();

        yachts = em.createQuery(
                "SELECT y FROM yachts AS y WHERE model_id LIKE :model_id")
                .setParameter("model_id", model_id)
                .getResultList();

        return yachts;
    }
}
