package com.sicimike.order.service.impl;

import com.sicimike.order.dto.OrderDTO;
import com.sicimike.order.repository.OrderDetailRepository;
import com.sicimike.order.repository.OrderMasterRepository;
import com.sicimike.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author sicimike
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(String orderId) {
        return null;
    }
}
