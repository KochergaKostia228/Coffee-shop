package org.example.dao.menuItemTypeDAO;

import org.example.dao.CRUDInterface;
import org.example.model.MenuItemType;

import java.util.List;

public interface MenuItemTypeDAO extends CRUDInterface<MenuItemType> {
    MenuItemType findById(long id);
}
