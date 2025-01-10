package org.example.server.order_menu_item;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dao.clientDAO.ClientDAO;
import org.example.dao.clientDAO.ClientImpl;
import org.example.dao.menuItemDAO.MenuItemDAO;
import org.example.dao.menuItemDAO.MenuItemImpl;
import org.example.dao.orderInfoDAO.OrderInfoDAO;
import org.example.dao.orderInfoDAO.OrderInfoImpl;
import org.example.dao.orderMenuItemDAO.OrderMenuItemDAO;
import org.example.dao.orderMenuItemDAO.OrderMenuItemImpl;
import org.example.model.Client;
import org.example.model.MenuItem;
import org.example.model.OrderInfo;
import org.example.model.OrderMenuItem;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderMenuItemServiceImpl implements OrderMenuItemService {
    @Override
    public void addOrderMenuItem(int orderInfoIndex, int menuItemIndex) {
        OrderInfoDAO orderInfoDAO = new OrderInfoImpl();
        MenuItemDAO menuItemDAO = new MenuItemImpl();
        OrderMenuItemDAO orderMenuItemDAO = new OrderMenuItemImpl();
        ClientDAO clientDAO = new ClientImpl();

        List<MenuItem> menuItemList = menuItemDAO.findAll();
        List<OrderInfo> orderInfoList = orderInfoDAO.findAll();
        try {
            MenuItem menuItem = menuItemList.get(menuItemIndex-1);
            OrderInfo orderInfo = orderInfoList.get(orderInfoIndex-1);
            Client client = clientDAO.findById(orderInfo.getClientId());

            OrderMenuItem addOrderMenuItem = new OrderMenuItem();
            addOrderMenuItem.setOrderId(orderInfo.getId());
            addOrderMenuItem.setMenuItemId(menuItem.getId());
            addOrderMenuItem.setPrice(menuItem.getPrice());
            orderMenuItemDAO.save(addOrderMenuItem);

            double percentConvert = menuItem.getPrice() * (client.getDiscountPercent()/100.0); // Снижка від вартості на товар
            double priceWithDiscount = orderInfo.getPriceWithDiscount() + menuItem.getPrice() - percentConvert; // Додаємо до існуючої вартості за заказ

            orderInfo.setPriceWithDiscount(priceWithDiscount);

            orderInfoDAO.update(orderInfo);

        } catch(IndexOutOfBoundsException e) {
            System.err.println("Invalid name of group");
            throw new RuntimeException();
        }

    }

    @Override
    public void updateOrderMenuItem(int orderMenuItemIndex, int orderInfoIndex, int menuItemIndex) {
        OrderInfoDAO orderInfoDAO = new OrderInfoImpl();
        MenuItemDAO menuItemDAO = new MenuItemImpl();
        OrderMenuItemDAO orderMenuItemDAO = new OrderMenuItemImpl();
        ClientDAO clientDAO = new ClientImpl();

        List<MenuItem> menuItemList = menuItemDAO.findAll();
        List<OrderInfo> orderInfoList = orderInfoDAO.findAll();
        List<OrderMenuItem> orderMenuItemList = orderMenuItemDAO.findAll();

        MenuItem menuItem = menuItemList.get(menuItemIndex-1);
        OrderInfo orderInfo = orderInfoList.get(orderInfoIndex-1);
        OrderMenuItem orderMenuItem = orderMenuItemList.get(orderMenuItemIndex-1);
        Client client = clientDAO.findById(orderInfo.getClientId());

        try {
            double doPriceIsMenuItem = orderMenuItem.getPrice() - orderMenuItem.getPrice() * (client.getDiscountPercent()/100.0);
            double fullPriceOrderInfo = orderInfo.getPriceWithDiscount() - doPriceIsMenuItem;

            orderInfo.setPriceWithDiscount(fullPriceOrderInfo);

            orderMenuItem.setOrderId(orderInfo.getId());
            orderMenuItem.setMenuItemId(menuItem.getId());
            orderMenuItem.setPrice(menuItem.getPrice());
            orderMenuItemDAO.update(orderMenuItem);

            double percentConvert = menuItem.getPrice() * (client.getDiscountPercent()/100.0); // Снижка від вартості на товар
            double priceWithDiscount = orderInfo.getPriceWithDiscount() + menuItem.getPrice() - percentConvert; // Додаємо до існуючої вартості за заказ

            orderInfo.setPriceWithDiscount(priceWithDiscount);

            orderInfoDAO.update(orderInfo);

        } catch(IndexOutOfBoundsException e) {
            System.err.println("Invalid name of group");
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteOrderMenuItemByIndex(int orderMenuItemIndex) {
        OrderMenuItemDAO orderMenuItemDAO = new OrderMenuItemImpl();
        OrderInfoDAO orderInfoDAO = new OrderInfoImpl();
        ClientDAO clientDAO = new ClientImpl();
        List<OrderMenuItem> orderMenuItemList = orderMenuItemDAO.findAll();
        try {
            OrderMenuItem orderMenuItem = orderMenuItemList.get(orderMenuItemIndex-1);
            OrderInfo orderInfo = orderInfoDAO.findById(orderMenuItem.getOrderId());
            Client client = clientDAO.findById(orderInfo.getClientId());

            orderMenuItemDAO.delete(orderMenuItem);

            double doPriceIsMenuItem = orderMenuItem.getPrice() - orderMenuItem.getPrice() * (client.getDiscountPercent()/100.0);
            double fullPriceOrderInfo = orderInfo.getPriceWithDiscount() - doPriceIsMenuItem;

            orderInfo.setPriceWithDiscount(fullPriceOrderInfo);

            orderInfoDAO.update(orderInfo);

        } catch(IndexOutOfBoundsException e) {
            System.err.println("Invalid name of group");
            throw new RuntimeException();
        }
    }
}
