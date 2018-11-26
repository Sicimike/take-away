package com.sicimike.order.client;

import com.sicimike.order.entity.ProductInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 调用商品服务
 * @Author sicimike
 */
@FeignClient(name = "product")
public interface ProductClient {

    @PostMapping("/product/listForOrder")
    public List<ProductInfo> listForOrder(@RequestBody List<String> productIdList);
}