package org.example.server.menu_item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dao.ConnectionFactory;
import org.example.dao.menuItemDAO.MenuItemDAO;
import org.example.dao.menuItemDAO.MenuItemImpl;
import org.example.dao.menuItemTypeDAO.MenuItemTypeDAO;
import org.example.dao.menuItemTypeDAO.MenuItemTypeImpl;
import org.example.exception.ConnectionDBException;
import org.example.model.MenuItem;
import org.example.model.MenuItemType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
public class MenuItemServiceImpl implements MenuItemService{

    @Override
    public Optional<MenuItem> addMenuItem(int menuItemTypeIndex, String nameUkraineLanguage, String nameEnglishLanguage, double price) {
        MenuItemDAO menuItemDAO = new MenuItemImpl();
        MenuItemTypeDAO menuItemTypeDAO = new MenuItemTypeImpl();
        List<MenuItemType> menuItemTypeList = menuItemTypeDAO.findAll();
        try {
            MenuItemType menuItemType1 = menuItemTypeList.get(menuItemTypeIndex-1);
            MenuItem addMenuItem = new MenuItem();
            addMenuItem.setTypeId(menuItemType1.getId());
            addMenuItem.setName_ukraine_language(nameUkraineLanguage);
            addMenuItem.setName_english_language(nameEnglishLanguage);
            addMenuItem.setPrice(price);
            menuItemDAO.save(addMenuItem);

        } catch(IndexOutOfBoundsException e) {
            System.err.println("Invalid name of group");
            throw new RuntimeException();
        }

        List<MenuItem> menuItemList = menuItemDAO.findAll();

        for (MenuItem menuItem : menuItemList) {
            if (menuItem.getName_ukraine_language().equals(nameUkraineLanguage) && menuItem.getName_english_language().equals(nameEnglishLanguage)) {
                return Optional.of(menuItem);
            }
        }
        return Optional.empty();
    }

    @Override
    public void updateMenuItemToPrice(int index, double price) {
        MenuItemDAO menuItemDAO = new MenuItemImpl();
        List<MenuItem> menuItemTypeList = menuItemDAO.findAll();
        try {
            MenuItem menuItem1 = menuItemTypeList.get(index-1);
            menuItem1.setPrice(price);
            menuItemDAO.update(menuItem1);

        } catch(IndexOutOfBoundsException e) {
            System.err.println("Invalid name of group");
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteMenuItem(int index) {
        MenuItemDAO menuItemDAO = new MenuItemImpl();
        List<MenuItem> menuItemTypeList = menuItemDAO.findAll();
        try {
            MenuItem menuItem1 = menuItemTypeList.get(index-1);
            menuItemDAO.delete(menuItem1);

        } catch(IndexOutOfBoundsException e) {
            System.err.println("Invalid name of group");
            throw new RuntimeException();
        }
    }
}
