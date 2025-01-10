package org.example.server.order_menu_item;

public interface OrderMenuItemService {
    void addOrderMenuItem(int orderInfoIndex, int menuItemIndex);

    void updateOrderMenuItem(int orderMenuItemIndex, int orderInfoIndex, int menuItemIndex);

    void deleteOrderMenuItemByIndex(int orderMenuItemIndex);
}
