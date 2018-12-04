package com.sicimike.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.sicimike.product.common.DecreaseStockInput;
import com.sicimike.product.common.ProductInfoOutput;
import com.sicimike.server.dto.CartDTO;
import com.sicimike.server.entity.ProductInfo;
import com.sicimike.server.enums.EnumProductStatus;
import com.sicimike.server.enums.EnumResult;
import com.sicimike.server.exception.ProductException;
import com.sicimike.server.repository.ProductInfoRepository;
import com.sicimike.server.service.ProductService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author sicimike
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(EnumProductStatus.UP.getCode());
    }

    @Override
    public List<ProductInfo> findList(List<String> productIdList) {
        return productInfoRepository.findByProductIdIn(productIdList);
    }

    @Override
    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {
        List<ProductInfo> productInfoList = decreaseStockProcess(decreaseStockInputList);
        List<ProductInfoOutput> productInfoOutputList = productInfoList.stream().map(e -> {
            ProductInfoOutput productInfoOutput = new ProductInfoOutput();
            BeanUtils.copyProperties(e, productInfoOutput);
            return productInfoOutput;
        }).collect(Collectors.toList());
        /**
         * 购物车中所有商品库存完成后发送MQ消息
         * 避免减扣过程出现异常，导致数据库和Redis库存数据不一致
         */
        amqpTemplate.convertAndSend("productInfo", JSON.toJSONString(productInfoOutputList));
    }

    /**
     * 减扣库存
     * @param decreaseStockInputList
     * @return
     */
    @Transactional(rollbackOn = Exception.class)
    protected List<ProductInfo> decreaseStockProcess(List<DecreaseStockInput> decreaseStockInputList){
        List<ProductInfo> productInfoList = new ArrayList<>();
        for (DecreaseStockInput decreaseStockInput : decreaseStockInputList) {
            Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(decreaseStockInput.getProductId());
            //判断商品是否存在
            if (!productInfoOptional.isPresent()) {
                throw new ProductException(EnumResult.PRODUCT_NOT_EXIST);
            }

            ProductInfo productInfo = productInfoOptional.get();
            Integer result = productInfo.getProductStock() - decreaseStockInput.getProductQuantity();
            if (result < 0) {
                //库存不足
                throw new ProductException(EnumResult.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
            productInfoList.add(productInfo);
        }
        return productInfoList;
    }
}
