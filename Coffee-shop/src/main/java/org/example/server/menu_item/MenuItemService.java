package org.example.server.menu_item;

import org.example.model.MenuItem;

import java.util.Optional;

public interface MenuItemService {
    Optional<MenuItem> addMenuItem(int menuItemTypeIndex, String nameUkraineLanguage, String nameEnglishLanguage, double price);

    void updateMenuItem(int index, int menuItemTypeIndex, MenuItem updateMenuItem);

    void deleteMenuItem(int index);

    MenuItem insertMenuItemToIndex(int index);
}
