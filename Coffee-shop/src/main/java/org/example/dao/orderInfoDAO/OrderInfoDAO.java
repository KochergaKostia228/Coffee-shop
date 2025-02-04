package org.example.dao.orderInfoDAO;

import org.example.dao.CRUDInterface;
import org.example.model.MenuItemType;
import org.example.model.OrderInfo;

public interface OrderInfoDAO extends CRUDInterface<OrderInfo> {
    OrderInfo findById(long id);

}
