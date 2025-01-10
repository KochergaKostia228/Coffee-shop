package org.example.server.order_info;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dao.clientDAO.ClientDAO;
import org.example.dao.clientDAO.ClientImpl;
import org.example.dao.menuItemDAO.MenuItemDAO;
import org.example.dao.menuItemDAO.MenuItemImpl;
import org.example.dao.menuItemTypeDAO.MenuItemTypeDAO;
import org.example.dao.menuItemTypeDAO.MenuItemTypeImpl;
import org.example.dao.orderInfoDAO.OrderInfoDAO;
import org.example.dao.orderInfoDAO.OrderInfoImpl;
import org.example.dao.personnelDAO.PersonnelDAO;
import org.example.dao.personnelDAO.PersonnelImpl;
import org.example.model.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
public class OrderInfoServiceImpl implements OrderInfoService{
    @Override
    public void addOrderInfo(int clientIndex) {
        OrderInfoDAO orderInfoDAO = new OrderInfoImpl();
        ClientDAO clientDAO = new ClientImpl();
        List<Client> clientList = clientDAO.findAll();
        try {
            Client client = clientList.get(clientIndex-1);

            OrderInfo addOrderInfo = new OrderInfo();
            addOrderInfo.setClientId(client.getId());
            addOrderInfo.setPriceWithDiscount(0.0);
            addOrderInfo.setDate(new Date(System.currentTimeMillis()));
            orderInfoDAO.save(addOrderInfo);

        } catch(IndexOutOfBoundsException e) {
            System.err.println("Invalid name of group");
            throw new RuntimeException();
        }

    }

    @Override
    public OrderInfo addOrderInfoAndReturn(int clientIndex) {
        OrderInfoDAO orderInfoDAO = new OrderInfoImpl();
        ClientDAO clientDAO = new ClientImpl();
        List<Client> clientList = clientDAO.findAll();
        try {
            Client client = clientList.get(clientIndex-1);

            OrderInfo addOrderInfo = new OrderInfo();
            addOrderInfo.setClientId(client.getId());
            addOrderInfo.setPriceWithDiscount(0.0);
            addOrderInfo.setDate(Date.valueOf(LocalDate.now()));
            orderInfoDAO.save(addOrderInfo);

            return addOrderInfo;
        } catch(IndexOutOfBoundsException e) {
            System.err.println("Invalid name of group");
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteOrderInfoByIndex(int orderInfoIndex) {
        OrderInfoDAO orderInfoDAO = new OrderInfoImpl();
        List<OrderInfo> orderInfoList = orderInfoDAO.findAll();
        try {
            OrderInfo orderInfo = orderInfoList.get(orderInfoIndex-1);
            orderInfoDAO.delete(orderInfo);

        } catch(IndexOutOfBoundsException e) {
            System.err.println("Invalid name of group");
            throw new RuntimeException();
        }
    }
}
