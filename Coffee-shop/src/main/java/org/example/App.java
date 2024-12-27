package org.example;

import org.example.dao.menuItemDAO.MenuItemImpl;
import org.example.dao.menuItemTypeDAO.MenuItemTypeImpl;
import org.example.dao.workScheduleDAO.WorkScheduleImpl;
import org.example.menu.MenuExecutor;
import org.example.model.MenuItem;
import org.example.model.MenuItemType;
import org.example.model.WorkSchedule;
import org.example.server.CoffeeShopInitializer;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.setProperty("test", "false");

        CoffeeShopInitializer school = new CoffeeShopInitializer();
        school.schoolInitialize();

        MenuExecutor.startMenu();
    }
}
