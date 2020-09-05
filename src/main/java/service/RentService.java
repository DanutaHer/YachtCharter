package service;

import entity.Customer;
import entity.Rent;
import entity.Yacht;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

public class RentService {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY2 = Persistence.createEntityManagerFactory("yachtcharter");

    private EntityManager em = ENTITY_MANAGER_FACTORY2.createEntityManager();

    public void addRent() {

        try {
            em.getTransaction().begin();
            Rent rent = new Rent();
            Customer customer = null;

            Scanner scanner = new Scanner(System.in);
            System.out.println("Give customer id");
            Long customerId = scanner.nextLong();

            customer = em.find(Customer.class, customerId);
            System.out.println(customer.getCustomerId() + ", " + customer.getName() + ", " + customer.getPhone() + ", " + customer.getEmail() + ", " + customer.getAddress());

            System.out.println("Give rented year of rented from");
            int year = scanner.nextInt();
            System.out.println("Give rented month of rented from");
            int month = scanner.nextInt();
            System.out.println("Give rented day of rented from");
            int day = scanner.nextInt();
            rent.setRentedFrom(LocalDate.of(year, month, day));

            System.out.println("Give rented year of rented to");
            int year2 = scanner.nextInt();
            System.out.println("Give rented month of rented to");
            int month2 = scanner.nextInt();
            System.out.println("Give rented day of rented to");
            int day2 = scanner.nextInt();
            rent.setRentedTo(LocalDate.of(year2, month2, day2));

            System.out.println("Give yacht id");
            Long yachtId = scanner.nextLong();
            rent.setYachtId(yachtId);


            String findQuery = "SELECT rent_id FROM \n" +
                    "rents \n" +
                    "WHERE yacht_id = :yachtId and rented_from >=':rentedFrom' and rented_to <= ':rentedTo'";

            Query query = em.createQuery(findQuery);
            query.getFirstResult();

            if (query.getFirstResult() != 0) {
                System.out.println("Cannot be saved");
            } else {

                rent.setCustomerId(customer.getCustomerId());

                em.persist(rent);
                em.getTransaction().commit();
                System.out.println("Rent added");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void getRent() {

        EntityTransaction et = null;
        Rent rent = null;

        try {
            Scanner scanner = new Scanner(System.in);
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
        Rent rent = null;

        try {
            et = em.getTransaction();
            et.begin();

            Scanner scanner = new Scanner(System.in);
            System.out.println("Give rent id number:");
            Long rentId = scanner.nextLong();

            rent = em.find(Rent.class, rentId);

            System.out.println("New yacht id:");
            Long newYachtId = scanner.nextLong();
            rent.setYachtId(newYachtId);

            System.out.println("New rent from:");
            String newRentFrom = scanner.nextLine();

            //rent.setRentedFrom();

            em.persist(rent);
            et.commit();
            System.out.println("Changes saved");

        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void deleteRent() {
        EntityTransaction et = null;
        Rent rent = null;

        try {
            et = em.getTransaction();
            et.begin();

            Scanner scanner = new Scanner(System.in);
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

//    public static void query(Long yachtId, LocalDate rentedFrom, LocalDate rentedTo, EntityManager em) {
//        EntityTransaction et = null;
//        et = em.getTransaction();
//        et.begin();
//
//        String findQuery = "SELECT * FROM \n" +
//                "rents \n" +
//                "WHERE yacht_id = :yachtId and rented_from >=':rentedFrom' and rented_to <= ':rentedTo';";
//
//        Query query = em.createQuery(findQuery);
//        em.close();
//    }

}
