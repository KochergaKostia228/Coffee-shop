package org.example.server.personnel;

import lombok.Data;
import lombok.NoArgsConstructor;
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
import java.util.Optional;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
public class PersonnelServiceImpl implements PersonnelService {

    @Override
    public Optional<Personnel> addPersonnel(int personnelPositionIndex, String firstName, String lastName, String middleName, String telephone, String address){
        PersonnelDAO personnelDAO = new PersonnelImpl();
        PersonnelPositionDAO personnelPositionDAO = new PersonnelPositionImpl();
        List<PersonnelPosition> personnelPositionList = personnelPositionDAO.findAll();
        try {
            PersonnelPosition personnelPosition1 = personnelPositionList.get(personnelPositionIndex-1);
            Personnel addPersonnel = new Personnel();
            addPersonnel.setPositionId(personnelPosition1.getId());
            addPersonnel.setFirstName(firstName);
            addPersonnel.setLastName(lastName);
            addPersonnel.setMiddleName(middleName);
            addPersonnel.setTelephone(telephone);
            addPersonnel.setAddress(address);
            personnelDAO.save(addPersonnel);

        } catch(IndexOutOfBoundsException e) {
            System.err.println("Invalid name of group");
            throw new RuntimeException();
        }

        List<Personnel> personnelList = personnelDAO.findAll();

        for (Personnel personnel : personnelList) {
            if (personnel.getFirstName().equals(firstName)
                    && personnel.getLastName().equals(lastName)
                    && personnel.getMiddleName().equals(middleName)
                    && personnel.getTelephone().equals(telephone)
                    && personnel.getAddress().equals(address)
            ) {
                return Optional.of(personnel);
            }
        }
        return Optional.empty();
    }

    @Override
    public void updatePersonnelToAddress(int indexPersonnel, String updateAddress) {
        PersonnelDAO personnelDAO = new PersonnelImpl();
        List<Personnel> personnelList = personnelDAO.findAll();
        try {
            Personnel personnel = personnelList.get(indexPersonnel-1);
            personnel.setAddress(updateAddress);
            personnelDAO.update(personnel);

        } catch(IndexOutOfBoundsException e) {
            System.err.println("Invalid name of group");
            throw new RuntimeException();
        }
    }

    @Override
    public void updatePersonnelToTelephone(int indexPersonnel, String updateTelephone) {
        PersonnelDAO personnelDAO = new PersonnelImpl();
        List<Personnel> personnelList = personnelDAO.findAll();
        try {
            Personnel personnel = personnelList.get(indexPersonnel-1);
            personnel.setTelephone(updateTelephone);
            personnelDAO.update(personnel);

        } catch(IndexOutOfBoundsException e) {
            System.err.println("Invalid name of group");
            throw new RuntimeException();
        }
    }

    @Override
    public void deletePersonnel(int index) {
        PersonnelDAO personnelDAO = new PersonnelImpl();
        List<Personnel> personnelList = personnelDAO.findAll();
        try {
            Personnel personnel = personnelList.get(index-1);
            personnelDAO.delete(personnel);

        } catch(IndexOutOfBoundsException e) {
            System.err.println("Invalid name of group");
            throw new RuntimeException();
        }
    }
}
