import service.CustomerService;
import service.YachtService;

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
            switch (scanner.next()) {
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

        System.out.println("================================");
        System.out.println("These are avaiable for a yacht:");
        System.out.println("1 - to add a new yacht you just bought");
        System.out.println("2 - to find a yacht");
        System.out.println("3 - to update sth e.g. yacht's price");
        System.out.println("4 - to remove a yacht you just sold");
        System.out.println("0 - to go back to the main menu");

        if (scanner.hasNext()) {
            switch (scanner.next()) {
                case "1":
                    System.out.println("Will add a yacht");
                    yachtService.addYacht();
                    printMenu();
                    break;
                case "2":
                    System.out.println("Will find a yacht");
                    // YachtService.find();
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
        System.out.println("=====================================");
        System.out.println("These are avaiable for a customer:");
        System.out.println("1 - to add a new customer");
        System.out.println("2 - to find a customer");
        System.out.println("3 - to update customer's data");
        System.out.println("4 - to remove a customer because he did not sign RODO");
        System.out.println("0 - to go back to the main menu");

        if (scanner.hasNext()) {
            switch (scanner.next()) {
                case "1":
                    System.out.println("Will add a customer");
                    CustomerService.addCustomer();
                    printMenu();
                    break;
                case "2":
                    System.out.println("Will display data for a chosen customer");
                    // CustomerService.find();
                    printMenu();
                    break;
                case "3":
                    System.out.println("Will update a customer");
                    // CustomerService.edit();
                    printMenu();
                    break;
                case "4":
                    System.out.println("Will remove a customer");
                    // CustomerService.delete();
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
        System.out.println("========================");
        System.out.println("Are you looking for:");
        System.out.println("1 - to make a reservation");
        System.out.println("2 - to find a reservation");
        System.out.println("3 - to change a reservation");
        System.out.println("4 - to cancel a reservation");
        System.out.println("0 - to go back to the main menu");

        if (scanner.hasNext()) {
            switch (scanner.next()) {
                case "1":
                    System.out.println("Will make a reservation");
                    // RentService.add();
                    printMenu();
                    break;
                case "2":
                    System.out.println("Will find a reservation");
                    // RentService.find(); // by customer, by id, by yacht, by date....
                    printMenu();
                    break;
                case "3":
                    System.out.println("Will change a reservation");
                    // RentService.edit();
                    printMenu();
                    break;
                case "4":
                    System.out.println("Will cancel a reservation");
                    // RentService.delete();
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
