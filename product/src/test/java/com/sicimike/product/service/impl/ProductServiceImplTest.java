package com.sicimike.product.service.impl;

import com.sicimike.product.ProductApplicationTests;
import com.sicimike.product.dto.CartDTO;
import com.sicimike.product.entity.ProductInfo;
import com.sicimike.product.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ProductServiceImplTest extends ProductApplicationTests {

    @Autowired
    private ProductService productService;

    @Test
    public void findUpAll() throws Exception {
        List<ProductInfo> list = productService.findUpAll();
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void decreaseStock() throws Exception{
        CartDTO cartDTO = new CartDTO("164103465734242707",2);
        productService.decreaseStock(Arrays.asList(cartDTO));
    }

}