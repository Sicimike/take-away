package com.sicimike.product.common;

import lombok.Data;

/**
 * 减库存入参
 * @author sicimike
 * @create 2018-11-27 10:05
 */
@Data
public class DecreaseStockInput {

    private String productId;

    private Integer productQuantity;

    public DecreaseStockInput() {
    }

    public DecreaseStockInput(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }

}
