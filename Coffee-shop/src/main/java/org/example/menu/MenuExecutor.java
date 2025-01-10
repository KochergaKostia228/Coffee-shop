package org.example.menu;



import org.example.dao.menuItemDAO.MenuItemDAO;
import org.example.dao.menuItemDAO.MenuItemImpl;
import org.example.dao.orderMenuItemDAO.OrderMenuItemDAO;
import org.example.dao.orderMenuItemDAO.OrderMenuItemImpl;
import org.example.model.*;
import org.example.server.client.ClientService;
import org.example.server.client.ClientServiceImpl;
import org.example.server.menu_item.MenuItemService;
import org.example.server.menu_item.MenuItemServiceImpl;
import org.example.server.order_info.OrderInfoService;
import org.example.server.order_info.OrderInfoServiceImpl;
import org.example.server.order_menu_item.OrderMenuItemService;
import org.example.server.order_menu_item.OrderMenuItemServiceImpl;
import org.example.server.personnel.PersonnelService;
import org.example.server.personnel.PersonnelServiceImpl;
import org.example.server.work_schedule.WorkScheduleServiceImpl;

import java.sql.Date;
import java.sql.Time;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Scanner;

import static org.example.menu.MenuPublisher.*;


public class MenuExecutor {

    private static MenuItemService menuItemService = new MenuItemServiceImpl();

    private static PersonnelService personnelService = new PersonnelServiceImpl();

    private static ClientService clientService = new ClientServiceImpl();

    private static OrderInfoService orderInfoService = new OrderInfoServiceImpl();

    private static OrderMenuItemService orderMenuItemService = new OrderMenuItemServiceImpl();

    private static WorkScheduleServiceImpl workScheduleService = new WorkScheduleServiceImpl();

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
            if (choice == 13) {
                menuItem13Execute();
            }
            if (choice == 14) {
                menuItem14Execute();
            }
            if (choice == 15) {
                menuItem15Execute();
            }
            if (choice == 16) {
                menuItem16Execute();
            }
            if (choice == 17) {
                menuItem17Execute();
            }
            if (choice == 18) {
                menuItem18Execute();
            }
            if (choice == 19) {
                menuItem19Execute();
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

        showMenuItemTypes();
        System.out.println("Please, enter the type to update menu item. If you do not want to change this field, then enter the value that was before");
        int menuItemTypeIndex = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Please, enter the name ukraine language of menu item. If you do not want to change this field, then enter the value that was before");
        String nameUkraineLanguage = scanner.nextLine();
        System.out.println("Please, enter the name english language of menu item. If you do not want to change this field, then enter the value that was before");
        String nameEnglishLanguage = scanner.nextLine();
        System.out.println("Please, enter the price of menu item. If you do not want to change this field, then enter the value that was before");
        double price = scanner.nextDouble();

        MenuItem menuItem = new MenuItem();
        menuItem.setName_ukraine_language(nameUkraineLanguage);
        menuItem.setName_english_language(nameEnglishLanguage);
        menuItem.setPrice(price);

        menuItemService.updateMenuItem(menuItemIndex, menuItemTypeIndex, menuItem);
    }

    public static void menuItem5Execute(){
        Scanner scanner = new Scanner(System.in);
        showClients();
        System.out.println("Please, enter the number");
        int clientIndex = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Please, enter the first name of client. If you do not want to change this field, then enter the value that was before");
        String firstName = scanner.nextLine();

        System.out.println("Please, enter the last name of client. If you do not want to change this field, then enter the value that was before");
        String lastName = scanner.nextLine();

        System.out.println("Please, enter the middle name of client. If you do not want to change this field, then enter the value that was before");
        String middleName = scanner.nextLine();

        System.out.println("Please, enter the day of date of birth. If you do not want to change this field, then enter the value that was before");
        int day = scanner.nextInt();

        System.out.println("Please, enter the month of date of birth. If you do not want to change this field, then enter the value that was before");
        int month = scanner.nextInt();

        System.out.println("Please, enter the year of date of birth. If you do not want to change this field, then enter the value that was before");
        int year = scanner.nextInt();
        scanner.nextLine();

        Date date = new Date(year, month, day);

        System.out.println("Please, enter the telephone of client. If you do not want to change this field, then enter the value that was before");
        String telephone = scanner.nextLine();

        System.out.println("Please, enter the address of client. If you do not want to change this field, then enter the value that was before");
        String address = scanner.nextLine();

        System.out.println("Please, enter the discount percent of client. If you do not want to change this field, then enter the value that was before");
        int discountPercent = scanner.nextInt();

        Client client = new Client();
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setMiddleName(middleName);
        client.setDateOfBirth(date);
        client.setAddress(address);
        client.setTelephone(telephone);
        client.setDiscountPercent(discountPercent);

        clientService.updateClient(clientIndex, client);
    }

    public static void menuItem6Execute(){
        Scanner scanner = new Scanner(System.in);
        showPersonnel();
        System.out.println("Please, enter the number");
        int clientIndex = scanner.nextInt();

        showPersonnelPositions();
        System.out.println("Please, enter the position to add personnel. If you do not want to change this field, then enter the value that was before");
        int personnelPositionIndex = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Please, enter the first name of personnel. If you do not want to change this field, then enter the value that was before");
        String firstName = scanner.nextLine();
        System.out.println("Please, enter the last name of personnel. If you do not want to change this field, then enter the value that was before");
        String lastName = scanner.nextLine();
        System.out.println("Please, enter the middle name of personnel. If you do not want to change this field, then enter the value that was before");
        String middleName = scanner.nextLine();
        System.out.println("Please, enter the telephone of personnel. If you do not want to change this field, then enter the value that was before");
        String telephone = scanner.nextLine();
        System.out.println("Please, enter the address of personnel. If you do not want to change this field, then enter the value that was before");
        String address = scanner.nextLine();

        Personnel personnel = new Personnel();
        personnel.setFirstName(firstName);
        personnel.setLastName(lastName);
        personnel.setMiddleName(middleName);
        personnel.setTelephone(telephone);
        personnel.setAddress(address);

        personnelService.updatePersonnel(clientIndex, personnelPositionIndex, personnel);
    }

    public static void menuItem7Execute(){
        Scanner scanner = new Scanner(System.in);
        showMenuItems();
        System.out.println("Please, enter the number");
        int menuItemIndex = scanner.nextInt();

        menuItemService.deleteMenuItem(menuItemIndex);
    }

    public static void menuItem8Execute(){
        Scanner scanner = new Scanner(System.in);
        showPersonnel();
        System.out.println("Please, enter the number");
        int personnelIndex = scanner.nextInt();

        personnelService.deletePersonnel(personnelIndex);
    }

    public static void menuItem9Execute(){
        Scanner scanner = new Scanner(System.in);
        showClients();
        System.out.println("Please, enter the number");
        int clientIndex = scanner.nextInt();

        clientService.deleteClient(clientIndex);
    }

    public static void menuItem10Execute(){
        showMenuItems();
    }

    public static void menuItem11Execute(){
        showPersonnel();
    }

    public static void menuItem12Execute(){
        Scanner scanner = new Scanner(System.in);
        showClients();
        System.out.println("Please, enter the number");
        int clientIndex = scanner.nextInt();

        orderInfoService.addOrderInfo(clientIndex);
    }

    public static void menuItem13Execute(){
        Scanner scanner = new Scanner(System.in);
        showOrderInfo();
        System.out.println("Please, enter the number");
        int orderInfoIndex = scanner.nextInt();

        showMenuItems();
        System.out.println("Please, enter the number");
        int menuItemIndex = scanner.nextInt();

        orderMenuItemService.addOrderMenuItem(orderInfoIndex, menuItemIndex);
    }

    public static void menuItem14Execute(){
        Scanner scanner = new Scanner(System.in);
        showDayOfWeek();
        System.out.println("Please, enter the number");
        int dayOfWeekIndex = scanner.nextInt();

        showPersonnel();
        System.out.println("Please, enter the number");
        int personnelIndex = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Please, enter the house start work in format HH:mm:ss");
        String stringStart = scanner.nextLine();
        Time dateStartWork = Time.valueOf(stringStart);
        System.out.println("Please, enter the house end work in format HH:mm:ss");
        String stringEnd = scanner.nextLine();
        Time dateEndWork = Time.valueOf(stringEnd);

        workScheduleService.addWorkScheduleSpecialDay(personnelIndex, dayOfWeekIndex, dateStartWork, dateEndWork);
    }

    public static void menuItem15Execute(){
        Scanner scanner = new Scanner(System.in);
        showWorkSchedule();
        System.out.println("Please, enter the number");
        int workScheduleIndex = scanner.nextInt();

        showDayOfWeek();
        System.out.println("Please, enter the number");
        int dayOfWeekIndex = scanner.nextInt();

        showPersonnel();
        System.out.println("Please, enter the number. If you do not want to change this field, then enter the value that was before");
        int personnelIndex = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Please, enter the house start work in format HH:mm:ss. If you do not want to change this field, then enter the value that was before");
        String stringStart = scanner.nextLine();
        Time dateStartWork = Time.valueOf(stringStart);
        System.out.println("Please, enter the house end work in format HH:mm:ss. If you do not want to change this field, then enter the value that was before");
        String stringEnd = scanner.nextLine();
        Time dateEndWork = Time.valueOf(stringEnd);

        WorkSchedule workSchedule = new WorkSchedule();
        workSchedule.setDayWeek(DayOfWeek.of(dayOfWeekIndex));
        workSchedule.setStartTimeWork(dateStartWork);
        workSchedule.setEndTimeWork(dateEndWork);

        workScheduleService.updateWorkSchedule(workScheduleIndex, personnelIndex,workSchedule);
    }

    public static void menuItem16Execute(){
        Scanner scanner = new Scanner(System.in);
        showOrderMenuItem();
        System.out.println("Please, enter the number");
        int orderMenuItemIndex = scanner.nextInt();

        showOrderInfo();
        System.out.println("Please, enter the number. If you do not want to change this field, then enter the value that was before");
        int orderInfoIndex = scanner.nextInt();

        showMenuItems();
        System.out.println("Please, enter the number. If you do not want to change this field, then enter the value that was before");
        int menuItemIndex = scanner.nextInt();
        scanner.nextLine();

        orderMenuItemService.updateOrderMenuItem(orderMenuItemIndex, orderInfoIndex,menuItemIndex);
    }

    public static void menuItem17Execute(){
        Scanner scanner = new Scanner(System.in);
        showOrderInfo();
        System.out.println("Please, enter the number");
        int orderMenuItemIndex = scanner.nextInt();

        orderInfoService.deleteOrderInfoByIndex(orderMenuItemIndex);
    }

    public static void menuItem18Execute(){
        Scanner scanner = new Scanner(System.in);
        showOrderMenuItem();
        System.out.println("Please, enter the number");
        int orderMenuItemIndex = scanner.nextInt();

        orderMenuItemService.deleteOrderMenuItemByIndex(orderMenuItemIndex);
    }

    public static void menuItem19Execute(){
        Scanner scanner = new Scanner(System.in);
        showWorkSchedule();
        System.out.println("Please, enter the number");
        int workScheduleIndex = scanner.nextInt();

        workScheduleService.deleteWorkScheduleByIndex(workScheduleIndex);
    }

    private MenuExecutor() {
    }

}
