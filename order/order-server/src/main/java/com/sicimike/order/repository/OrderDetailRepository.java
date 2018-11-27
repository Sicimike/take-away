package com.sicimike.order.repository;

import com.sicimike.order.entity.OrderDetail;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author sicimike
 */
public interface OrderDetailRepository extends CrudRepository<OrderDetail, String> {

    /**
     * 根据订单id查找
     * @param orderId
     * @return
     */
    List<OrderDetail> findByOrderId(String orderId);

}
