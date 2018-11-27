package com.sicimike.server.repository;

import com.sicimike.server.ProductServerApplicationTests;
import com.sicimike.server.entity.ProductCategory;
import com.sicimike.server.repository.ProductCategoryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

public class ProductCategoryRepositoryTest extends ProductServerApplicationTests {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void findByCategoryTypeIn() throws Exception {
        List<ProductCategory> list = productCategoryRepository.findByCategoryTypeIn(Arrays.asList(11, 22));
        Assert.assertTrue(list.size() > 0);
    }

}