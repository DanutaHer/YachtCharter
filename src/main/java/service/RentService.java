package service;

import entity.Customer;
import entity.Model;
import entity.Rent;
import util.PersistenceManager;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * Represents rent service
 *
 * @author Danuta Hering
 */

public class RentService {
    private EntityManager em = PersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
    private Scanner scanner = new Scanner(System.in);
    private Rent rent = new Rent();
    private Customer customer = null;
    private Model model = new Model();

    public void addRent() {

        System.out.println("Give rented from yyyy-mm-dd");
        String rentedFrom = scanner.nextLine();
        LocalDate localDate = LocalDate.parse(rentedFrom);
        System.out.println("Give rented to yyyy-mm-dd");
        String rentedTo = scanner.nextLine();
        LocalDate localDate2 = LocalDate.parse(rentedTo);
        System.out.println("Give yacht id");
        Long yachtId = scanner.nextLong();

        try {

            List<Rent> rents = em.createQuery("SELECT r FROM Rent r WHERE r.yachtId =:yachtId and r.rentedFrom >= :rentedFrom and r.rentedTo <= :rentedTo", Rent.class)
                    .setParameter("yachtId", yachtId)
                    .setParameter("rentedFrom", localDate)
                    .setParameter("rentedTo", localDate2)
                    .getResultList();

            if (rents.isEmpty()) {

                em.getTransaction().begin();
                rent.setRentedFrom(localDate);
                rent.setRentedTo(localDate2);
                rent.setYachtId(yachtId);

                System.out.println("Give customer id");
                Long customerId = scanner.nextLong();
                customer = em.find(Customer.class, customerId);
                System.out.println(customer.getCustomerId() + ", " + customer.getName() + ", " + customer.getPhone() + ", " + customer.getEmail() + ", " + customer.getAddress());
                rent.setCustomerId(customer.getCustomerId());

                Long costDays = ChronoUnit.DAYS.between(localDate, localDate2);
                model = em.find(Model.class, yachtId);
                BigDecimal cost = model.getPricePerDay();
                BigDecimal endCost = cost.multiply(BigDecimal.valueOf(costDays));
                System.out.println("Cost = " + endCost + " PLN");
                rent.setCost(endCost);
                em.persist(rent);
                em.getTransaction().commit();
                System.out.println("Rent added");

            } else {
                RentService rentService = new RentService();
                System.out.println("Deadline not available, give new parameters");
                rentService.addRent();
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            em.close();
        }
    }

    public void getRent() {

        EntityTransaction et = null;

        try {
            System.out.println("Give rent id number:");
            Long rentId = scanner.nextLong();

            rent = em.find(Rent.class, rentId);
            System.out.println("Rent id" + rent.getRentId() + ", Yacht id" + rent.getYachtId() + ", " + rent.getRentedFrom() + ", " + rent.getRentedTo());

        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public List<Rent> findRentByYachtId(Long yachtId){
        List<Rent> rents = em.createQuery("SELECT r FROM Rent as r WHERE yachtId LIKE :yachtId", Rent.class)
                .setParameter("yachtId", yachtId)
                .getResultList();

        return rents;
    }

    public List<Rent> findRentByRentedFrom(LocalDate rentedFrom){
        List<Rent> rents = em.createQuery("SELECT r FROM Rent as r WHERE rentedFrom LIKE :rentedFrom", Rent.class)
                .setParameter("rentedFrom", rentedFrom)
                .getResultList();

        return rents;
    }

    public List<Rent> findRentByRentedTo(LocalDate rentedTo){
        List<Rent> rents = em.createQuery("SELECT r FROM Rent as r WHERE rentedFrom LIKE :rentedTo", Rent.class)
                .setParameter("rentedTo", rentedTo)
                .getResultList();

        return rents;
    }
    public void editRent() {

        EntityTransaction et = null;

        em.getTransaction().begin();
        System.out.println("Give rent id number:");
        Long rentId = scanner.nextLong();
        rent = em.find(Rent.class, rentId);
        System.out.println("Your reservation: " + rent);
        System.out.println("Find new reservation");

        System.out.println("Give rented from yyyy-mm-dd");
        String rentedFrom = scanner.nextLine();
        String rentedFrom2 = scanner.nextLine();
        LocalDate localDateFrom = LocalDate.parse(rentedFrom2);
        System.out.println("Give rented to yyyy-mm-dd");
        String rentedTo = scanner.nextLine();
        LocalDate localDateTo = LocalDate.parse(rentedTo);
        System.out.println("Give yacht id");
        Long yachtId = scanner.nextLong();

        try {
            List<Rent> rents = em.createQuery("SELECT r FROM Rent r WHERE r.yachtId =:yachtId and r.rentedFrom >= :rentedFrom2 and r.rentedTo <= :rentedTo", Rent.class)
                    .setParameter("yachtId", yachtId)
                    .setParameter("rentedFrom2", LocalDate.parse(rentedFrom2))
                    .setParameter("rentedTo", LocalDate.parse(rentedTo))
                    .getResultList();

            if (rents.isEmpty()) {

                rent.setRentedFrom(localDateFrom);
                rent.setRentedTo(localDateTo);
                rent.setYachtId(yachtId);

                Long costDays = ChronoUnit.DAYS.between(localDateFrom, localDateTo);
                model = em.find(Model.class, yachtId);
                BigDecimal cost = model.getPricePerDay();
                BigDecimal endCost = cost.multiply(BigDecimal.valueOf(costDays));
                System.out.println("Cost = " + endCost + " PLN");
                rent.setCost(endCost);

                em.persist(rent);
                em.getTransaction().commit();
                System.out.println("Rent added");

            } else {

                RentService rentService = new RentService();
                System.out.println("Deadline not available, give new parameters");
                rentService.addRent();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void deleteRent() {
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();

            System.out.println("Give rent id number:");
            Long rentId = scanner.nextLong();

            rent = em.find(Rent.class, rentId);
            em.remove(rent);

            et.commit();
        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            em.close();
        }

    }

    private void costByDay(Long modelId){
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();

            model = em.find(Model.class, modelId);

            et.commit();
        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }


}
