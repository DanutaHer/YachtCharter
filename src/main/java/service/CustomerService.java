package service;

import entity.Customer;

import javax.persistence.*;

import java.util.Scanner;

public class CustomerService {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("yachtcharter");

    private EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

    public void addCustomer() {

        try {
            em.getTransaction().begin();
            Customer customer = new Customer();

            Scanner scanner = new Scanner(System.in);
            System.out.println("First and last name:");
            String name = scanner.nextLine();
            customer.setName(name);
            System.out.println("Phone number:");
            String phone = scanner.nextLine();
            customer.setPhone(phone);
            System.out.println("Email:");
            String email = scanner.nextLine();
            customer.setEmail(email);
            System.out.println("Address:");
            String address = scanner.nextLine();
            customer.setAddress(address);

            em.persist(customer);
            em.getTransaction().commit();
            System.out.println("Customer " + name + " added");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void getCustomer() {

        EntityTransaction et = null;
        Customer cust = null;

        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Give customer id number:");
            Long customerId = scanner.nextLong();

            cust = em.find(Customer.class, customerId);
            System.out.println(cust.getName()+ ", " + cust.getPhone()+ ", " + cust.getEmail() + ", " + cust.getAddress());

        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void editCustomer() {

        EntityTransaction et = null;
        Customer cust = null;

        try {
            et = em.getTransaction();
            et.begin();

            Scanner scanner = new Scanner(System.in);
            System.out.println("Give customer id number:");
            Long customerId = scanner.nextLong();

            cust = em.find(Customer.class, customerId);

            System.out.println("New name:");
            String newName = scanner.nextLine();
            cust.setName(newName);

            System.out.println("New phone:");
            String newPhone = scanner.nextLine();
            cust.setPhone(newPhone);

            System.out.println("New email:");
            String newEmail = scanner.nextLine();
            cust.setEmail(newEmail);

            System.out.println("New address:");
            String newAddress = scanner.nextLine();
            cust.setAddress(newAddress);

            em.persist(cust);
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

    public void deleteCustomer() {
        EntityTransaction et = null;
        Customer cust = null;

        try {
            et = em.getTransaction();
            et.begin();

            Scanner scanner = new Scanner(System.in);
            System.out.println("Give customer id number:");
            Long customerId = scanner.nextLong();

            cust = em.find(Customer.class, customerId);
            em.remove(cust);

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