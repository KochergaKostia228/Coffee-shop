package org.example.menu;



import org.example.dao.menuItemDAO.MenuItemDAO;
import org.example.dao.menuItemDAO.MenuItemImpl;
import org.example.server.client.ClientService;
import org.example.server.client.ClientServiceImpl;
import org.example.server.menu_item.MenuItemService;
import org.example.server.menu_item.MenuItemServiceImpl;
import org.example.server.personnel.PersonnelService;
import org.example.server.personnel.PersonnelServiceImpl;

import java.sql.Date;
import java.util.Scanner;

import static org.example.menu.MenuPublisher.*;


public class MenuExecutor {

    private static MenuItemService menuItemService = new MenuItemServiceImpl();

    private static PersonnelService personnelService = new PersonnelServiceImpl();

    private static ClientService clientService = new ClientServiceImpl();

    public static void startMenu() {
        while (true){
            showMenu();

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            if (choice == 1) {
                menuItem1Execute();
            }
            if (choice == 2) {
                menuItem2Execute();
            }
            if (choice == 3) {
                menuItem3Execute();
            }
            if (choice == 4) {
                menuItem4Execute();
            }
            if (choice == 5) {
                menuItem5Execute();
            }
            if (choice == 6) {
                menuItem6Execute();
            }
            if (choice == 7) {
                menuItem7Execute();
            }
            if (choice == 8) {
                menuItem8Execute();
            }
            if (choice == 9) {
                menuItem9Execute();
            }
            if (choice == 10) {
                menuItem10Execute();
            }
            if (choice == 11) {
                menuItem11Execute();
            }
            if (choice == 12) {
                menuItem12Execute();
            }
            if (choice == 0) {
                break;
            }
        }
    }

    public static void menuItem1Execute() {
        Scanner scanner = new Scanner(System.in);
        showMenuItemTypes();
        System.out.println("Please, enter the type to add menu item");
        int menuItemTypeIndex = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Please, enter the name ukraine language of menu item");
        String nameUkraineLanguage = scanner.nextLine();
        System.out.println("Please, enter the name english language of menu item");
        String nameEnglishLanguage = scanner.nextLine();
        System.out.println("Please, enter the price of menu item");
        double price = scanner.nextDouble();

        menuItemService.addMenuItem(menuItemTypeIndex, nameUkraineLanguage, nameEnglishLanguage, price);
    }

    public static void menuItem2Execute() {
        Scanner scanner = new Scanner(System.in);
        showPersonnelPositions();
        System.out.println("Please, enter the position to add personnel");
        int personnelPositionIndex = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Please, enter the first name of personnel");
        String firstName = scanner.nextLine();
        System.out.println("Please, enter the last name of personnel");
        String lastName = scanner.nextLine();
        System.out.println("Please, enter the middle name of personnel");
        String middleName = scanner.nextLine();
        System.out.println("Please, enter the telephone of personnel");
        String telephone = scanner.nextLine();
        System.out.println("Please, enter the address of personnel");
        String address = scanner.nextLine();

        personnelService.addPersonnel(personnelPositionIndex, firstName, lastName, middleName, telephone, address);
    }

    public static void menuItem3Execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please, enter the first name of client");
        String firstName = scanner.nextLine();

        System.out.println("Please, enter the last name of client");
        String lastName = scanner.nextLine();

        System.out.println("Please, enter the middle name of client");
        String middleName = scanner.nextLine();

        System.out.println("Please, enter the day of date of birth");
        int day = scanner.nextInt();

        System.out.println("Please, enter the month of date of birth");
        int month = scanner.nextInt();

        System.out.println("Please, enter the year of date of birth");
        int year = scanner.nextInt();
        scanner.nextLine();

        Date date = new Date(year, month, day);

        System.out.println("Please, enter the telephone of client");
        String telephone = scanner.nextLine();

        System.out.println("Please, enter the address of client");
        String address = scanner.nextLine();

        System.out.println("Please, enter the discount percent of client");
        int discountPercent = scanner.nextInt();

        clientService.addClient(firstName, lastName, middleName, date, telephone, address, discountPercent);
    }

    public static void menuItem4Execute() {
        Scanner scanner = new Scanner(System.in);
        showMenuItems();
        System.out.println("Please, enter the number");
        int menuItemIndex = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Please, enter the price of menu item");
        double price = scanner.nextDouble();

        menuItemService.updateMenuItemToPrice(menuItemIndex, price);
    }

    public static void menuItem5Execute(){
        Scanner scanner = new Scanner(System.in);
        showClients();
        System.out.println("Please, enter the number");
        int clientIndex = scanner.nextInt();

        System.out.println("Please, enter the update discount percent of client");
        int discountPercent = scanner.nextInt();

        clientService.updateClientToDiscountPercent(clientIndex, discountPercent);
    }

    public static void menuItem6Execute(){
        Scanner scanner = new Scanner(System.in);
        showPersonnel();
        System.out.println("Please, enter the number");
        int clientIndex = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Please, enter the update address of personnel");
        String address = scanner.nextLine();

        personnelService.updatePersonnelToAddress(clientIndex, address);
    }

    public static void menuItem7Execute(){
        Scanner scanner = new Scanner(System.in);
        showClients();
        System.out.println("Please, enter the number");
        int clientIndex = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Please, enter the update telephone of personnel");
        String telephone = scanner.nextLine();

        personnelService.updatePersonnelToTelephone(clientIndex, telephone);
    }

    public static void menuItem8Execute(){
        Scanner scanner = new Scanner(System.in);
        showMenuItems();
        System.out.println("Please, enter the number");
        int menuItemIndex = scanner.nextInt();

        menuItemService.deleteMenuItem(menuItemIndex);
    }

    public static void menuItem9Execute(){
        Scanner scanner = new Scanner(System.in);
        showPersonnel();
        System.out.println("Please, enter the number");
        int personnelIndex = scanner.nextInt();

        personnelService.deletePersonnel(personnelIndex);
    }

    public static void menuItem10Execute(){
        Scanner scanner = new Scanner(System.in);
        showClients();
        System.out.println("Please, enter the number");
        int clientIndex = scanner.nextInt();

        clientService.deleteClient(clientIndex);
    }

    public static void menuItem11Execute(){
        showMenuItems();
    }

    public static void menuItem12Execute(){
        showPersonnel();
    }

    private MenuExecutor() {
    }

}
