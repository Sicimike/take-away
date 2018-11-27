package com.sicimike.server.repository;

import com.sicimike.server.ProductServerApplicationTests;
import com.sicimike.server.ProductServerApplicationTests;
import com.sicimike.server.entity.ProductInfo;
import com.sicimike.server.repository.ProductInfoRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductInfoRepositoryTest extends ProductServerApplicationTests {

    @Autowired
    ProductInfoRepository productInfoRepository;

    @Test
    public void findByProductStatus() throws Exception {
        List<ProductInfo> list = productInfoRepository.findByProductStatus(0);
        Assert.assertTrue(list.size() > 0);
    }

}