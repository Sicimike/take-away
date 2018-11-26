package com.sicimike.order.controller;

import com.alibaba.fastjson.JSON;
import com.sicimike.order.client.ProductClient;
import com.sicimike.order.converter.OrderForm2OrderDtoConverter;
import com.sicimike.order.dto.CartDTO;
import com.sicimike.order.dto.OrderDTO;
import com.sicimike.order.entity.ProductInfo;
import com.sicimike.order.enums.EnumResult;
import com.sicimike.order.exception.OrderException;
import com.sicimike.order.form.OrderForm;
import com.sicimike.order.service.OrderService;
import com.sicimike.order.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author sicimike
 */
@Slf4j
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductClient productClient;

    /**
     * 1. 参数检验
     * 2. 查询商品信息(调用商品服务)
     * 3. 计算总价
     * 4. 扣库存(调用商品服务)
     * 5. 订单入库
     */
    @PostMapping("/order/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm,
                                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确, orderForm={}", orderForm);
            throw new OrderException(EnumResult.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        // orderForm -> orderDTO
        OrderDTO orderDTO = OrderForm2OrderDtoConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车信息为空");
            throw new OrderException(EnumResult.CART_EMPTY);
        }

        OrderDTO result = orderService.create(orderDTO);

        Map<String, String> map = new HashMap<>(1);
        map.put("orderId", result.getOrderId());
        return ResultVO.success(map);
    }

    @GetMapping("/order/getProductList")
    public String getProductList(){
        List<ProductInfo> list = productClient.listForOrder(Arrays.asList("157875196366160022","164103465734242707"));
        log.info(JSON.toJSONString(list));
        return "OK";
    }

    @GetMapping("/order/productDecreaseStock")
    public String productDecreaseStock(){
        productClient.decreaseStock(Arrays.asList(new CartDTO("157875196366160022", 2)));
        return "OK";
    }

}
