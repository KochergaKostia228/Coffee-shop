package org.example.menu;

import org.example.dao.clientDAO.ClientDAO;
import org.example.dao.clientDAO.ClientImpl;
import org.example.dao.menuItemDAO.MenuItemDAO;
import org.example.dao.menuItemDAO.MenuItemImpl;
import org.example.dao.menuItemTypeDAO.MenuItemTypeDAO;
import org.example.dao.menuItemTypeDAO.MenuItemTypeImpl;
import org.example.dao.orderInfoDAO.OrderInfoDAO;
import org.example.dao.orderInfoDAO.OrderInfoImpl;
import org.example.dao.orderMenuItemDAO.OrderMenuItemDAO;
import org.example.dao.orderMenuItemDAO.OrderMenuItemImpl;
import org.example.dao.personnelDAO.PersonnelDAO;
import org.example.dao.personnelDAO.PersonnelImpl;
import org.example.dao.personnelPositionDAO.PersonnelPositionDAO;
import org.example.dao.personnelPositionDAO.PersonnelPositionImpl;
import org.example.dao.workScheduleDAO.WorkScheduleDAO;
import org.example.dao.workScheduleDAO.WorkScheduleImpl;
import org.example.model.*;

import java.time.DayOfWeek;
import java.util.List;

public class MenuPublisher {

    private static final String ACTION_STRING = "To do action press the number";

    private static final String ADD_MENU_ITEM = "Add new menu item";
    private static final String ADD_PERSONNEL = "Add new personnel";
    private static final String ADD_CLIENT = "Add new client";
    private static final String ADD_ORDER_INFO = "Create order info from a specific client";
    private static final String ADD_ORDER_MENU_ITEM = "Add menu item from a specific order";
    private static final String ADD_WORK_SCHEDULE = "Add a work schedule for a specific day of the week";


    private static final String UPDATE_MENU_ITEM = "Update to menu item";
    private static final String UPDATE_CLIENT = "Update to client";
    private static final String UPDATE_PERSONNEL = "Update to personnel";
    private static final String UPDATE_WORK_SCHEDULE = "Update to work schedule";
    private static final String UPDATE_ORDER_MENU_ITEM = "Update to menu item from";


    private static final String DELETE_TO_MENU_ITEM = "Delete to menu item";
    private static final String DELETE_TO_CLIENT = "Delete to client";
    private static final String DELETE_TO_PERSONNEL = "Delete to personnel";
    private static final String DELETE_TO_ORDER_INFO = "Delete to order info";
    private static final String DELETE_TO_ORDER_MENU_ITEM = "Delete to menu item from";
    private static final String DELETE_TO_WORK_SCHEDULE = "Delete to work schedule";

    private static final String SELECT_TO_MENU_ITEM = "Select to menu item";
    private static final String SELECT_TO_PERSONNEL = "Select to personnel";

    private static final String INVATION_STRING = "Please enter the number";
    private static final String SEPARATOR = "-";
    private static final String DOT_SPACE = ".  ";
    private static final String END_LINE = "\n";

    private static final String LIST_OF_MENU_ITEM_TYPE = "List of menu item type";
    private static final String LIST_OF_MENU_ITEM = "List of menu item";
    private static final String LIST_OF_PERSONNEL_POSITION = "List of personnel position";
    private static final String LIST_OF_CLIENT = "List of client";
    private static final String LIST_OF_PERSONNEL = "List of personnel";
    private static final String LIST_OF_WORK_SCHEDULE = "List of work schedule";
    private static final String LIST_OF_ORDER_INFO = "List of order info";
    private static final String LIST_OF_ORDER_MENU_ITEM = "List of order menu item";
    private static final String LIST_OF_DAY_WEEK = "List of day week";

    public static void showMenu() {

        int menuLine = 1;
        StringBuilder resultString = new StringBuilder();
        resultString.append(ACTION_STRING)
                .append(END_LINE)
                .append(SEPARATOR.repeat(60))
                .append(END_LINE)
                .append(menuLine++)
                .append(DOT_SPACE)
                .append(ADD_MENU_ITEM)
                .append(END_LINE)
                .append(menuLine++)
                .append(DOT_SPACE)
                .append(ADD_PERSONNEL)
                .append(END_LINE)
                .append(menuLine++)
                .append(DOT_SPACE)
                .append(ADD_CLIENT)
                .append(END_LINE)
                .append(menuLine++)
                .append(DOT_SPACE)
                .append(UPDATE_MENU_ITEM)
                .append(END_LINE)
                .append(menuLine++)
                .append(DOT_SPACE)
                .append(UPDATE_CLIENT)
                .append(END_LINE)
                .append(menuLine++)
                .append(DOT_SPACE)
                .append(UPDATE_PERSONNEL)
                .append(END_LINE)
                .append(menuLine++)
                .append(DOT_SPACE)
                .append(DELETE_TO_MENU_ITEM)
                .append(END_LINE)
                .append(menuLine++)
                .append(DOT_SPACE)
                .append(DELETE_TO_PERSONNEL)
                .append(END_LINE)
                .append(menuLine++)
                .append(DOT_SPACE)
                .append(DELETE_TO_CLIENT)
                .append(END_LINE)
                .append(menuLine++)
                .append(DOT_SPACE)
                .append(SELECT_TO_MENU_ITEM)
                .append(END_LINE)
                .append(menuLine++)
                .append(DOT_SPACE)
                .append(SELECT_TO_PERSONNEL)
                .append(END_LINE)
                .append(menuLine++)
                .append(DOT_SPACE)
                .append(ADD_ORDER_INFO)
                .append(END_LINE)
                .append(menuLine++)
                .append(DOT_SPACE)
                .append(ADD_ORDER_MENU_ITEM)
                .append(END_LINE)
                .append(menuLine++)
                .append(DOT_SPACE)
                .append(ADD_WORK_SCHEDULE)
                .append(END_LINE)
                .append(menuLine++)
                .append(DOT_SPACE)
                .append(UPDATE_WORK_SCHEDULE)
                .append(END_LINE)
                .append(menuLine++)
                .append(DOT_SPACE)
                .append(UPDATE_ORDER_MENU_ITEM)
                .append(END_LINE)
                .append(menuLine++)
                .append(DOT_SPACE)
                .append(DELETE_TO_ORDER_INFO)
                .append(END_LINE)
                .append(menuLine++)
                .append(DOT_SPACE)
                .append(DELETE_TO_ORDER_MENU_ITEM)
                .append(END_LINE)
                .append(menuLine++)
                .append(DOT_SPACE)
                .append(DELETE_TO_WORK_SCHEDULE)
                .append(END_LINE)
                .append(SEPARATOR.repeat(60))
                .append(END_LINE)
                .append(INVATION_STRING);

        System.out.println(resultString.toString());
    }

    public static void showMenuItemTypes() {
        MenuItemTypeDAO menuItemTypeDAO = new MenuItemTypeImpl();
        List<MenuItemType> menuItemTypeList = menuItemTypeDAO.findAll();

        int menuLine = 1;
        StringBuilder resultString = new StringBuilder();

        resultString.append(LIST_OF_MENU_ITEM_TYPE)
                .append(END_LINE)
                .append(SEPARATOR.repeat(60))
                .append(END_LINE);

        for (var item : menuItemTypeList) {
            resultString.append(menuLine++)
                    .append(DOT_SPACE)
                    .append(item.getName())
                    .append(END_LINE);
        }
        resultString.append(SEPARATOR.repeat(60))
                .append(END_LINE);

        System.out.println(resultString.toString());
    }

    public static void showMenuItems() {
        MenuItemTypeDAO menuItemTypeDAO = new MenuItemTypeImpl();
        MenuItemDAO menuItemDAO = new MenuItemImpl();
        List<MenuItem> menuItemList = menuItemDAO.findAll();

        int menuLine = 1;
        StringBuilder resultString = new StringBuilder();

        resultString.append(LIST_OF_MENU_ITEM)
                .append(END_LINE)
                .append(SEPARATOR.repeat(60))
                .append(END_LINE);

        for (var item : menuItemList) {
            resultString.append(menuLine++)
                    .append(DOT_SPACE)
                    .append("Type: ")
                    .append(menuItemTypeDAO.findById(item.getTypeId()).getName())
                    .append(", ")
                    .append("Name ukraine language: ")
                    .append(item.getName_ukraine_language())
                    .append(", ")
                    .append("Name english language: ")
                    .append(item.getName_english_language())
                    .append(", ")
                    .append("Price: ")
                    .append(item.getPrice())
                    .append(END_LINE);
        }
        resultString.append(SEPARATOR.repeat(60))
                .append(END_LINE);

        System.out.println(resultString.toString());
    }

    public static void showClients() {
        ClientDAO clientDAO = new ClientImpl();
        List<Client> clientList = clientDAO.findAll();

        int menuLine = 1;
        StringBuilder resultString = new StringBuilder();

        resultString.append(LIST_OF_CLIENT)
                .append(END_LINE)
                .append(SEPARATOR.repeat(60))
                .append(END_LINE);

        for (var item : clientList) {
            resultString.append(menuLine++)
                    .append(DOT_SPACE)
                    .append("First name: ")
                    .append(item.getFirstName())
                    .append(", ")
                    .append("Last name: ")
                    .append(item.getLastName())
                    .append(", ")
                    .append("Middle name: ")
                    .append(item.getMiddleName())
                    .append(", ")
                    .append("Date of birth: ")
                    .append(item.getDateOfBirth())
                    .append(", ")
                    .append("Telephone: ")
                    .append(item.getTelephone())
                    .append(", ")
                    .append("Address: ")
                    .append(item.getAddress())
                    .append(", ")
                    .append("Discount percent: ")
                    .append(item.getDiscountPercent())
                    .append(END_LINE);
        }
        resultString.append(SEPARATOR.repeat(60))
                .append(END_LINE);

        System.out.println(resultString.toString());
    }

    public static void showPersonnel() {
        PersonnelDAO personnelDAO = new PersonnelImpl();
        PersonnelPositionDAO personnelPositionDAO = new PersonnelPositionImpl();
        List<Personnel> personnelList = personnelDAO.findAll();

        int menuLine = 1;
        StringBuilder resultString = new StringBuilder();

        resultString.append(LIST_OF_PERSONNEL)
                .append(END_LINE)
                .append(SEPARATOR.repeat(60))
                .append(END_LINE);

        for (var item : personnelList) {
            resultString.append(menuLine++)
                    .append(DOT_SPACE)
                    .append("First name: ")
                    .append(item.getFirstName())
                    .append(", ")
                    .append("Last name: ")
                    .append(item.getLastName())
                    .append(", ")
                    .append("Middle name: ")
                    .append(item.getMiddleName())
                    .append(", ")
                    .append("Telephone: ")
                    .append(item.getTelephone())
                    .append(", ")
                    .append("Address: ")
                    .append(item.getAddress())
                    .append(", ")
                    .append("Position: ")
                    .append(personnelPositionDAO.findById(item.getPositionId()).getName())
                    .append(END_LINE);
        }
        resultString.append(SEPARATOR.repeat(60))
                .append(END_LINE);

        System.out.println(resultString.toString());
    }

    public static void showOrderInfo() {
        OrderInfoDAO orderInfoDAO = new OrderInfoImpl();
        ClientDAO clientDAO = new ClientImpl();
        List<OrderInfo> orderInfoList = orderInfoDAO.findAll();

        int menuLine = 1;
        StringBuilder resultString = new StringBuilder();

        resultString.append(LIST_OF_ORDER_INFO)
                .append(END_LINE)
                .append(SEPARATOR.repeat(60))
                .append(END_LINE);

        for (var item : orderInfoList) {
            resultString.append(menuLine++)
                    .append(DOT_SPACE)
                    .append("First name client: ")
                    .append(clientDAO.findById(item.getClientId()).getFirstName())
                    .append(", ")
                    .append("Last name client: ")
                    .append(clientDAO.findById(item.getClientId()).getLastName())
                    .append(", ")
                    .append("Middle name client: ")
                    .append(clientDAO.findById(item.getClientId()).getMiddleName())
                    .append(", ")
                    .append("Price with discount: ")
                    .append(item.getPriceWithDiscount())
                    .append(", ")
                    .append("Date: ")
                    .append(item.getDate())
                    .append(END_LINE);
        }
        resultString.append(SEPARATOR.repeat(60))
                .append(END_LINE);

        System.out.println(resultString.toString());
    }

    public static void showPersonnelPositions() {
        PersonnelPositionDAO personnelPositionDAO = new PersonnelPositionImpl();
        List<PersonnelPosition> personnelPositionList = personnelPositionDAO.findAll();

        int menuLine = 1;
        StringBuilder resultString = new StringBuilder();

        resultString.append(LIST_OF_PERSONNEL_POSITION)
                .append(END_LINE)
                .append(SEPARATOR.repeat(60))
                .append(END_LINE);

        for (var item : personnelPositionList) {
            resultString.append(menuLine++)
                    .append(DOT_SPACE)
                    .append(item.getName())
                    .append(END_LINE);
        }
        resultString.append(SEPARATOR.repeat(60))
                .append(END_LINE);

        System.out.println(resultString.toString());
    }

    public static void showWorkSchedule() {
        WorkScheduleDAO workScheduleDAO = new WorkScheduleImpl();
        PersonnelDAO personnelDAO = new PersonnelImpl();
        List<WorkSchedule> workScheduleList = workScheduleDAO.findAll();

        int menuLine = 1;
        StringBuilder resultString = new StringBuilder();

        resultString.append(LIST_OF_WORK_SCHEDULE)
                .append(END_LINE)
                .append(SEPARATOR.repeat(60))
                .append(END_LINE);

        for (var item : workScheduleList) {
            resultString.append(menuLine++)
                    .append(DOT_SPACE)
                    .append("First name personnel: ")
                    .append(personnelDAO.findById(item.getPersonnelId()).getFirstName())
                    .append(", ")
                    .append("Last name personnel: ")
                    .append(personnelDAO.findById(item.getPersonnelId()).getLastName())
                    .append(", ")
                    .append("Middle name personnel: ")
                    .append(personnelDAO.findById(item.getPersonnelId()).getMiddleName())
                    .append(", ")
                    .append("Day of week: ")
                    .append(item.getDayWeek())
                    .append(", ")
                    .append("Start time work: ")
                    .append(item.getStartTimeWork())
                    .append(", ")
                    .append("End time work: ")
                    .append(item.getEndTimeWork())
                    .append(", ")
                    .append(END_LINE);
        }
        resultString.append(SEPARATOR.repeat(60))
                .append(END_LINE);

        System.out.println(resultString.toString());
    }

    public static void showOrderMenuItem() {
        OrderMenuItemDAO orderMenuItemDAO = new OrderMenuItemImpl();
        MenuItemDAO menuItemDAO = new MenuItemImpl();
        OrderInfoDAO orderInfoDAO = new OrderInfoImpl();
        ClientDAO clientDAO = new ClientImpl();
        List<OrderMenuItem> orderMenuItems = orderMenuItemDAO.findAll();

        int menuLine = 1;
        StringBuilder resultString = new StringBuilder();

        resultString.append(LIST_OF_ORDER_MENU_ITEM)
                .append(END_LINE)
                .append(SEPARATOR.repeat(60))
                .append(END_LINE);

        for (var item : orderMenuItems) {
            OrderInfo orderInfo = orderInfoDAO.findById(item.getOrderId());
            resultString.append(menuLine++)
                    .append(DOT_SPACE)
                    .append("First name client: ")
                    .append(clientDAO.findById(orderInfo.getClientId()).getFirstName())
                    .append(", ")
                    .append("Last name client: ")
                    .append(clientDAO.findById(orderInfo.getClientId()).getLastName())
                    .append(", ")
                    .append("Middle name client: ")
                    .append(clientDAO.findById(orderInfo.getClientId()).getMiddleName())
                    .append(", ")
                    .append("Menu item ukraine name: ")
                    .append(menuItemDAO.findById(item.getMenuItemId()).getName_ukraine_language())
                    .append(", ")
                    .append("Price: ")
                    .append(item.getPrice())
                    .append(", ")
                    .append(END_LINE);
        }
        resultString.append(SEPARATOR.repeat(60))
                .append(END_LINE);

        System.out.println(resultString.toString());
    }

    public static void showDayOfWeek() {
        int menuLine = 1;
        StringBuilder resultString = new StringBuilder();

        resultString.append(LIST_OF_DAY_WEEK)
                .append(END_LINE)
                .append(SEPARATOR.repeat(60))
                .append(END_LINE);

        for (int i = 1; i <= 7; i++) {
            resultString.append(menuLine++)
                    .append(DOT_SPACE)
                    .append(DayOfWeek.of(i))
                    .append(END_LINE);
        }
        resultString.append(SEPARATOR.repeat(60))
                .append(END_LINE);

        System.out.println(resultString.toString());
    }
}
