package com.sicimike.product.repository;

import com.sicimike.product.entity.ProductInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductInfoRepository extends CrudRepository<ProductInfo, String>{

    /**
     * 根据商品状态查询
     * @param status
     * @return
     */
    List<ProductInfo> findByProductStatus(Integer status);
}
