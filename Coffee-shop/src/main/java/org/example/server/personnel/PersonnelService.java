package org.example.server.personnel;

import org.example.model.MenuItem;
import org.example.model.Personnel;

import java.util.Optional;

public interface PersonnelService {
    Optional<Personnel> addPersonnel(int personnelPositionIndex, String firstName, String lastName, String middleName, String telephone, String address);

    void updatePersonnel(int indexPersonnel, int personnelPositionIndex, Personnel updatePersonnel);

    void deletePersonnel(int index);
}
