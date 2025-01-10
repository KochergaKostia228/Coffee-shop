package org.example.dao.menuItemDAO;

import org.example.dao.CRUDInterface;
import org.example.model.MenuItem;

public interface MenuItemDAO extends CRUDInterface<MenuItem> {
    MenuItem findById(long id);
}
