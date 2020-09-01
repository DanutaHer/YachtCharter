package service;

import entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.Scanner;

public class CustomerService {


    @PersistenceContext
    public static void addCustomer() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("yachtcharter");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Customer customer = new Customer();

            Scanner scanner = new Scanner(System.in);
            System.out.println("Podaj imię i nazwisko");
            String name = scanner.nextLine();
            customer.setName(name);
            System.out.println("Podaj telefon");
            String phone = scanner.nextLine();
            customer.setPhone(phone);
            System.out.println("Podaj email");
            String email = scanner.nextLine();
            customer.setEmail(email);
            System.out.println("Podaj adres");
            String address = scanner.nextLine();
            customer.setAddress(address);

            em.persist(customer);
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }

    }
}


