package org.example.menu;

import org.example.dao.clientDAO.ClientDAO;
import org.example.dao.clientDAO.ClientImpl;
import org.example.dao.menuItemDAO.MenuItemDAO;
import org.example.dao.menuItemDAO.MenuItemImpl;
import org.example.dao.menuItemTypeDAO.MenuItemTypeDAO;
import org.example.dao.menuItemTypeDAO.MenuItemTypeImpl;
import org.example.dao.personnelDAO.PersonnelDAO;
import org.example.dao.personnelDAO.PersonnelImpl;
import org.example.dao.personnelPositionDAO.PersonnelPositionDAO;
import org.example.dao.personnelPositionDAO.PersonnelPositionImpl;
import org.example.model.*;

import java.util.List;

public class MenuPublisher {

    private static final String ACTION_STRING = "To do action press the number";

    private static final String ADD_MENU_ITEM = "Add new menu item";
    private static final String ADD_PERSONNEL = "Add new personnel";
    private static final String ADD_CLIENT = "Add new client";

    private static final String UPDATE_PRICE_TO_MENU_ITEM = "Update price to menu item";
    private static final String UPDATE_DISCOUNT_PERCENT_TO_CLIENT = "Update discount percent to client";
    private static final String UPDATE_ADDRESS_TO_PERSONNEL = "Update address to personnel";
    private static final String UPDATE_TELEPHONE_TO_PERSONNEL = "Update telephone to personnel";

    private static final String DELETE_TO_MENU_ITEM = "Delete to menu item";
    private static final String DELETE_TO_CLIENT = "Delete to client";
    private static final String DELETE_TO_PERSONNEL = "Delete to personnel";

    private static final String SELECT_TO_MENU_ITEM = "Select to menu item";
    private static final String SELECT_TO_PERSONNEL = "Select to personnel";

    private static final String INVATION_STRING = "Please enter the number";
    private static final String SEPARATOR = "-";
    private static final String DOT_SPACE = ".  ";
    private static final String END_LINE = "\n";

    private static final String LIST_OF_MENU_ITEM_TYPE = "List of menu item type";
    private static final String LIST_OF_MENU_ITEM = "List of menu item";
    private static final String LIST_OF_PERSONNEL_POSITION = "List of personnel position";
    private static final String LIST_OF_CLIENT = "List of personnel client";
    private static final String LIST_OF_PERSONNEL = "List of personnel personnel";

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
                .append(UPDATE_PRICE_TO_MENU_ITEM)
                .append(END_LINE)
                .append(menuLine++)
                .append(DOT_SPACE)
                .append(UPDATE_DISCOUNT_PERCENT_TO_CLIENT)
                .append(END_LINE)
                .append(menuLine++)
                .append(DOT_SPACE)
                .append(UPDATE_ADDRESS_TO_PERSONNEL)
                .append(END_LINE)
                .append(menuLine++)
                .append(DOT_SPACE)
                .append(UPDATE_TELEPHONE_TO_PERSONNEL)
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

}
