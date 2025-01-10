package org.example.server.order_info;

import org.example.model.Client;
import org.example.model.OrderInfo;

import java.sql.Date;
import java.util.Optional;

public interface OrderInfoService {
    void addOrderInfo(int clientIndex);

    OrderInfo addOrderInfoAndReturn(int clientIndex);

    void deleteOrderInfoByIndex(int orderInfoIndex);
}
