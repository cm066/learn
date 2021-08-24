package com.cm.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cm.order.client.AccountClient;
import com.cm.order.client.ProducerClient;
import com.cm.order.client.StorageClient;
import com.cm.order.entity.Orders;
import com.cm.order.mapper.OrdersMapper;
import com.cm.order.service.OrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrderService {


    @Autowired
    private StorageClient storageClient;

    @Autowired
    private AccountClient accountClient;

    @Autowired
    OrdersMapper ordersMapper;
    @Autowired
    ProducerClient producerClient;

    @GlobalTransactional
    @Override
    public boolean insertOrder(Orders order) {
        int insert = baseMapper.insert(order);
        accountClient.updateMOney1(123456L, 200);
        producerClient.testProducer();
        System.out.println(RootContext.getXID());
        return insert > 0 ? true : false;
    }


    @GlobalTransactional
    @Override
    public boolean toOrder(Long id, Long userId, Integer moneyFrozens, Integer ticketFrozens) {

        storageClient.toTicket(id, ticketFrozens);
        accountClient.updateMOney(userId, moneyFrozens);
        Orders orders = new Orders();
        orders.setProductNum(ticketFrozens);
        orders.setProductId(id);
        orders.setMoney(moneyFrozens);
        orders.setUserId(userId);
        orders.setStatus(0);
        int insert = ordersMapper.insert(orders);
        return insert > 0;
    }
}
