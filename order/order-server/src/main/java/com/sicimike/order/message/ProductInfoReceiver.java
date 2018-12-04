package com.sicimike.order.message;

import com.alibaba.fastjson.JSON;
import com.sicimike.product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author sicimike
 * @create 2018-12-04 13:59
 */
@Slf4j
@Component
@Transactional(rollbackOn = Exception.class)
public class ProductInfoReceiver {

    private static final String PRODUCT_STOCK_TEMPLATE = "product_stock_%s";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void process(String message){
        List<ProductInfoOutput> productInfoOutputList = JSON.parseArray(message, ProductInfoOutput.class);
        log.info("Get message from 【{}】 : {}", "productInfo", productInfoOutputList);

        productInfoOutputList.forEach(productInfoOutput -> {
            //存储到redis
            stringRedisTemplate.opsForValue().set(String.format(PRODUCT_STOCK_TEMPLATE, productInfoOutput.getProductId()), productInfoOutput.getProductStock()+"");
        });
    }
}
