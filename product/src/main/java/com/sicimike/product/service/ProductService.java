package com.sicimike.product.service;

import com.sicimike.product.entity.ProductInfo;

import java.util.List;

public interface ProductService {

    /**
     * 查询所有上线商品
     * @return
     */
    List<ProductInfo> findUpAll();

    /**
     * 查询商品列表
     * @param productIdList
     * @return
     */
    List<ProductInfo> findList(List<String> productIdList);
}
