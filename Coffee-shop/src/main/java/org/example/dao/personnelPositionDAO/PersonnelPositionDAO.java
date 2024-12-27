package org.example.dao.personnelPositionDAO;

import org.example.dao.CRUDInterface;
import org.example.model.MenuItemType;
import org.example.model.PersonnelPosition;

public interface PersonnelPositionDAO extends CRUDInterface<PersonnelPosition> {
    PersonnelPosition findById(long id);
}
