package com.sicimike.server.service;

import com.sicimike.server.dto.CartDTO;
import com.sicimike.server.entity.ProductInfo;

import java.util.List;

/**
 * @Author sicimike
 */
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

    void decreaseStock(List<CartDTO> cartDTOList);
}
