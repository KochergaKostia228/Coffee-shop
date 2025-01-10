package org.example.server.client;

import org.example.model.Client;
import org.example.model.MenuItem;
import org.example.model.Personnel;

import java.sql.Date;
import java.util.Optional;

public interface ClientService {
    Optional<Client> addClient(String firstName, String lastName, String middleName, Date dateOfBirth, String telephone, String address, int discountPercent);

    void updateClient(int indexClient, Client updateClient);

    void deleteClient(int index);
}
