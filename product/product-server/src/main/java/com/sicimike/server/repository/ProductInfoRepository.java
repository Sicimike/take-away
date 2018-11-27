package com.sicimike.server.repository;

import com.sicimike.server.entity.ProductInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author sicimike
 */
public interface ProductInfoRepository extends CrudRepository<ProductInfo, String>{

    /**
     * 根据商品状态查询
     * @param status
     * @return
     */
    List<ProductInfo> findByProductStatus(Integer status);

    /**
     * 根据商品id列表查询
     * @param productIdList
     * @return
     */
    List<ProductInfo> findByProductIdIn(List<String> productIdList);
}
