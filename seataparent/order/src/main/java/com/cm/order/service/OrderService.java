package com.cm.order.service;

import com.cm.order.entity.Order;
import com.cm.order.entity.Orders;
import org.springframework.web.bind.annotation.PathVariable;

public interface OrderService {

    boolean insertOrder(Orders order);

    boolean toOrder(Long id,Long userId,Integer moneyFrozens,Integer ticketFrozens);
}
