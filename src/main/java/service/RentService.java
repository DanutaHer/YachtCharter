package service;

import entity.Customer;
import entity.Rent;
import util.PersistenceManager;

import javax.persistence.*;

import java.time.LocalDate;

import java.util.*;

public class RentService {
    EntityManager em = PersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
    Scanner scanner = new Scanner(System.in);
    Rent rent = new Rent();
    Customer customer = null;

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

    public void editRent() {

        EntityTransaction et = null;

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
                    .setParameter("rentedFrom", rentedFrom)
                    .setParameter("rentedTo", rentedTo)
                    .getResultList();

            if (rents.isEmpty()) {
                em.getTransaction().begin();
                System.out.println("Give rent id number:");
                Long rentId = scanner.nextLong();
                rent = em.find(Rent.class, rentId);

                rent.setRentedFrom(localDate);
                rent.setRentedTo(localDate2);
                rent.setYachtId(yachtId);

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

}
