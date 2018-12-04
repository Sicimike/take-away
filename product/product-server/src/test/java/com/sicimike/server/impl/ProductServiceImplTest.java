package com.sicimike.server.impl;

import com.sicimike.product.common.DecreaseStockInput;
import com.sicimike.server.ProductServerApplicationTests;
import com.sicimike.server.dto.CartDTO;
import com.sicimike.server.entity.ProductInfo;
import com.sicimike.server.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ProductServiceImplTest extends ProductServerApplicationTests {

    @Autowired
    private ProductService productService;

    @Test
    public void findUpAll() throws Exception {
        List<ProductInfo> list = productService.findUpAll();
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void decreaseStock() throws Exception{
        DecreaseStockInput decreaseStockInput = new DecreaseStockInput("164103465734242707",2);
        productService.decreaseStock(Arrays.asList(decreaseStockInput));
    }

}