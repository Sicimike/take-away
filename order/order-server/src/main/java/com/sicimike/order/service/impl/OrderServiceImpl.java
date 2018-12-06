package com.sicimike.order.service.impl;

import com.sicimike.order.dto.OrderDTO;
import com.sicimike.order.entity.OrderDetail;
import com.sicimike.order.entity.OrderMaster;
import com.sicimike.order.enums.EnumOrderStatus;
import com.sicimike.order.enums.EnumPayStatus;
import com.sicimike.order.enums.EnumResult;
import com.sicimike.order.exception.OrderException;
import com.sicimike.order.repository.OrderDetailRepository;
import com.sicimike.order.repository.OrderMasterRepository;
import com.sicimike.order.service.OrderService;
import com.sicimike.order.utils.KeyUtil;
import com.sicimike.product.client.ProductClient;
import com.sicimike.product.common.DecreaseStockInput;
import com.sicimike.product.common.ProductInfoOutput;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author sicimike
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private ProductClient productClient;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();

        //调用商品服务，查询商品信息
        List<String> productIdList = orderDTO.getOrderDetailList().stream()
                .map(OrderDetail::getProductId)
                .collect(Collectors.toList());
        List<ProductInfoOutput> productInfoList = productClient.listForOrder(productIdList);

        BigDecimal orderAmout = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            for (ProductInfoOutput productInfo : productInfoList) {
                if (productInfo.getProductId().equals(orderDetail.getProductId())) {
                    //单价*数量
                    orderAmout = productInfo.getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(orderAmout);
                    BeanUtils.copyProperties(productInfo, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.genUniqueKey());
                    //订单详情入库
                    orderDetailRepository.save(orderDetail);
                }
            }
        }

        //调用商品服务，减扣库存
        List<DecreaseStockInput> decreaseStockInputList = orderDTO.getOrderDetailList().stream()
                .map(e -> new DecreaseStockInput(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productClient.decreaseStock(decreaseStockInputList);

        //订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmout);
        orderMaster.setOrderStatus(EnumOrderStatus.NEW.getCode());
        orderMaster.setPayStatus(EnumPayStatus.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public OrderDTO finish(String orderId) {
        //1. 查询订单
        Optional<OrderMaster> orderMasterOptional = orderMasterRepository.findById(orderId);
        if (!orderMasterOptional.isPresent()) {
            throw new OrderException(EnumResult.ORDER_NOT_EXIST);
        }
        //2. 校验状态
        OrderMaster orderMaster = orderMasterOptional.get();
        if (!EnumOrderStatus.NEW.getCode().equals(orderMaster.getOrderStatus())) {
            throw new OrderException(EnumResult.ORDER_STATUS_ERROR);
        }
        //3. 置为完成
        orderMaster.setOrderStatus(EnumOrderStatus.FINISHED.getCode());
        orderMasterRepository.save(orderMaster);
        //4. 查询订单详情
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new OrderException(EnumResult.ORDER_DETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
