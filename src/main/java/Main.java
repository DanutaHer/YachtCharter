import entity.Customer;
import entity.Yacht;
import service.CustomerService;
import service.ModelService;
import service.RentService;
import service.YachtService;

import java.util.List;
import java.util.Scanner;

/**
 * @author Marta Polcyn
 * @author Danuta Hering
 *
 * Project name: Yacht charter
 */

public class Main {

    final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        printMenu();
    }

    private static void printMenu() {
        System.out.println("================================");
        System.out.println("Welcome to our Yacht charter!");
        System.out.println("Please select an option:");
        System.out.println("1 - to make a reservation of a yacht");
        System.out.println("2 - to add/find/modify/remove a customer ");
        System.out.println("3 - to add/find/modify/remove a yacht");
        System.out.println("0 - to end the program");

        if (scanner.hasNext()) {
            switch (scanner.nextLine()) {
                case "1":
                    printMenuForRentOperations();
                    break;
                case "2":
                    printMenuForCustomerOperations();
                    break;
                case "3":
                    printMenuForYachtOperations();
                    break;
                case "0":
                    System.out.println("End of the program.");
                    break;
                default:
                    System.out.println("Choose a valid option!");
                    printMenu();
                    break;
            }
        }
    }

    private static void printMenuForYachtOperations() {

        YachtService yachtService = new YachtService();
        ModelService modelService = new ModelService();

        System.out.println("================================");
        System.out.println("These are avaiable for a yacht:");
        System.out.println("1 - to add a new yacht you just bought");
        System.out.println("2 - to find a yacht");
        System.out.println("3 - to update sth e.g. yacht's price");
        System.out.println("4 - to remove a yacht you just sold");
        System.out.println("0 - to go back to the main menu");

        if (scanner.hasNext()) {
            switch (scanner.nextLine()) {
                case "1":
                    System.out.println("Will add a yacht");
                    yachtService.addYacht();
                    printMenu();
                    break;
                case "2":
                    System.out.println("Will find a yacht");
                    System.out.println("Name: ");
                    String name = scanner.nextLine();
                    List<Yacht> yachts = yachtService.findYacht(name);
                    System.out.println("I found " + yachts.size() + " yachts ot this model");
                    System.out.println(modelService.findModel(name).get(0).toString());
                    printMenu();
                    break;
                case "3":
                    System.out.println("Will update a yacht");
                    // YachtService.edit();
                    printMenu();
                    break;
                case "4":
                    System.out.println("Will remove a yacht");
                    // YachtService.delete();
                    printMenu();
                    break;
                case "0":
                    System.out.println("Ok, I will go back");
                    printMenu();
                    break;
                default:
                    System.out.println("Choose a valid option!");
                    printMenuForYachtOperations();
                    break;
            }
        }
    }

    private static void printMenuForCustomerOperations() {
        CustomerService customerService = new CustomerService();

        System.out.println("=====================================");
        System.out.println("These are avaiable for a customer:");
        System.out.println("1 - to add a new customer");
        System.out.println("2 - to find a customer");
        System.out.println("3 - to update customer's data");
        System.out.println("4 - to remove a customer because he did not sign RODO");
        System.out.println("0 - to go back to the main menu");


        if (scanner.hasNext()) {
            switch (scanner.nextLine()) {
                case "1":
                    System.out.println("Will add a customer");
                    customerService.addCustomer();
                    printMenu();
                    break;
                case "2":
                    System.out.println("Will display data for a chosen customer");
                    printMenuForCustomerOperationsFindCustomer();
                    break;
                case "3":
                    System.out.println("Will update a customer");
                    customerService.editCustomer();
                    printMenu();
                    break;
                case "4":
                    System.out.println("Will remove a customer");
                    customerService.deleteCustomer();
                    printMenu();
                    break;
                case "0":
                    System.out.println("Going back");
                    printMenu();
                    break;
                default:
                    System.out.println("Choose a valid option!");
                    printMenuForYachtOperations();
                    break;
            }
        }
    }

    private static void printMenuForRentOperations() {

        RentService rentService = new RentService();
        System.out.println("========================");
        System.out.println("Are you looking for:");
        System.out.println("1 - to make a reservation");
        System.out.println("2 - to find a reservation");
        System.out.println("3 - to change a reservation");
        System.out.println("4 - to cancel a reservation");
        System.out.println("0 - to go back to the main menu");

        if (scanner.hasNext()) {
            switch (scanner.nextLine()) {
                case "1":
                    System.out.println("Will make a reservation");
                    rentService.addRent();
                    printMenu();
                    break;
                case "2":
                    System.out.println("Will find a reservation");
                    rentService.getRent();
                    printMenu();
                    break;
                case "3":
                    System.out.println("Will change a reservation");
                    rentService.editRent();
                    printMenu();
                    break;
                case "4":
                    System.out.println("Will cancel a reservation");
                    rentService.deleteRent();
                    printMenu();
                    break;
                case "0":
                    System.out.println("Going back");
                    printMenu();
                    break;
                default:
                    System.out.println("Choose a valid option!");
                    printMenuForYachtOperations();
                    break;
            }
        }

    }

    private static void printMenuForCustomerOperationsFindCustomer() {
        CustomerService customerService = new CustomerService();

        System.out.println("=====================================");
        System.out.println("Find customer:");
        System.out.println("1 - by number id");
        System.out.println("2 - by name");
        System.out.println("0 - to go back to the main menu");


        if (scanner.hasNext()) {
            switch (scanner.nextLine()) {
                case "1":
                    System.out.println("Give customer number id");
                    customerService.getCustomer();
                    printMenu();
                    break;
                case "2":
                    System.out.println("Give customer name");
                    List<Customer> customers = customerService.findCustomerByName();
                    System.out.println("I found " + customers.size() + " customers");
                    for (Customer cust:customers) {
                        System.out.println(cust.toString());
                    }
                    printMenu();
                    break;
                case "0":
                    System.out.println("Going back");
                    printMenu();
                    break;
                default:
                    System.out.println("Choose a valid option!");
                    printMenuForYachtOperations();
                    break;
            }
        }
    }

}
