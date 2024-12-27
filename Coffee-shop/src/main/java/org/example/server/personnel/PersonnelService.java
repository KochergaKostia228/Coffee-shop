package org.example.server.personnel;

import org.example.model.MenuItem;
import org.example.model.Personnel;

import java.util.Optional;

public interface PersonnelService {
    Optional<Personnel> addPersonnel(int personnelPositionIndex, String firstName, String lastName, String middleName, String telephone, String address);

    void updatePersonnelToAddress(int indexPersonnel, String updateAddress);

    void updatePersonnelToTelephone(int indexPersonnel, String updateTelephone);

    void deletePersonnel(int index);
}
