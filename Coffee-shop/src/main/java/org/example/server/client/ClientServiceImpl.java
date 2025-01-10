package org.example.server.client;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dao.clientDAO.ClientDAO;
import org.example.dao.clientDAO.ClientImpl;
import org.example.dao.menuItemDAO.MenuItemDAO;
import org.example.dao.menuItemDAO.MenuItemImpl;
import org.example.dao.menuItemTypeDAO.MenuItemTypeDAO;
import org.example.dao.menuItemTypeDAO.MenuItemTypeImpl;
import org.example.model.Client;
import org.example.model.MenuItem;
import org.example.model.MenuItemType;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
public class ClientServiceImpl implements ClientService{
    @Override
    public Optional<Client> addClient(String firstName, String lastName, String middleName, Date dateOfBirth, String telephone, String address, int discountPercent) {
        ClientDAO clientDAO = new ClientImpl();
        try {
            Client addClient = new Client();
            addClient.setFirstName(firstName);
            addClient.setLastName(lastName);
            addClient.setMiddleName(middleName);
            addClient.setDateOfBirth(dateOfBirth);
            addClient.setTelephone(telephone);
            addClient.setAddress(address);
            addClient.setDiscountPercent(discountPercent);
            clientDAO.save(addClient);

        } catch(IndexOutOfBoundsException e) {
            System.err.println("Invalid name of group");
            throw new RuntimeException();
        }

        List<Client> clientList = clientDAO.findAll();

        for (Client client : clientList) {

            if (client.getFirstName().equals(firstName)
                    && client.getLastName().equals(lastName)
                    && client.getMiddleName().equals(middleName)
                    && client.getTelephone().equals(telephone)
                    && client.getAddress().equals(address)
            ) {
                return Optional.of(client);
            }
        }
        return Optional.empty();
    }

    @Override
    public void updateClient(int indexClient, Client updateClient) {
        ClientDAO clientDAO = new ClientImpl();
        List<Client> clientList = clientDAO.findAll();
        try {
            Client client = clientList.get(indexClient-1);
            client.setFirstName(updateClient.getFirstName());
            client.setLastName(updateClient.getLastName());
            client.setMiddleName(updateClient.getMiddleName());
            client.setDateOfBirth(updateClient.getDateOfBirth());
            client.setAddress(updateClient.getAddress());
            client.setTelephone(updateClient.getTelephone());
            client.setDiscountPercent(updateClient.getDiscountPercent());

            clientDAO.update(client);

        } catch(IndexOutOfBoundsException e) {
            System.err.println("Invalid name of group");
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteClient(int index) {
        ClientDAO clientDAO = new ClientImpl();
        List<Client> clientList = clientDAO.findAll();
        try {
            Client client = clientList.get(index-1);
            clientDAO.delete(client);

        } catch(IndexOutOfBoundsException e) {
            System.err.println("Invalid name of group");
            throw new RuntimeException();
        }
    }
}
