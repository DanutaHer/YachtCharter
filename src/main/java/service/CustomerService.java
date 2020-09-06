package service;

import entity.Customer;
import util.PersistenceManager;
import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

/**
 * Represents customer service
 *
 * @author Danuta Hering
 */

public class CustomerService {

    private EntityManager em = PersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
    private Customer customer = new Customer();
    private Scanner scanner = new Scanner(System.in);
    private EntityTransaction et = null;
    private Customer cust = null;

    public void addCustomer() {

        try {
            em.getTransaction().begin();

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

        try {
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

    public List<Customer> findCustomerByName(){

        String name = scanner.nextLine();

        List<Customer> customers = em.createQuery("SELECT c FROM Customer as c WHERE name LIKE :name", Customer.class)
                .setParameter("name", name)
                .getResultList();

        return customers;
    }

    public List<Customer> findCustomerByPhone(){

        String phone = scanner.nextLine();

        List<Customer> customers = em.createQuery("SELECT c FROM Customer as c WHERE phone LIKE :phone", Customer.class)
                .setParameter("phone", phone)
                .getResultList();

        return customers;
    }

    public void editCustomer() {

        try {
            et = em.getTransaction();
            et.begin();

            System.out.println("Give customer id number:");
            Long customerId = scanner.nextLong();

            cust = em.find(Customer.class, customerId);

            System.out.println("New name:");
            String newNam = scanner.nextLine();
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

        try {
            et = em.getTransaction();
            et.begin();

            System.out.println("Give customer id number:");
            Long customerId = scanner.nextLong();

            cust = em.find(Customer.class, customerId);
            em.remove(cust);

            et.commit();
            System.out.println("Customer deletes");
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