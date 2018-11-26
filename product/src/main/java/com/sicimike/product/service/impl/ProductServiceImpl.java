package com.sicimike.product.service.impl;

import com.sicimike.product.dto.CartDTO;
import com.sicimike.product.entity.ProductInfo;
import com.sicimike.product.enums.EnumProductStatus;
import com.sicimike.product.enums.EnumResult;
import com.sicimike.product.exception.ProductException;
import com.sicimike.product.repository.ProductInfoRepository;
import com.sicimike.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @Author sicimike
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(EnumProductStatus.UP.getCode());
    }

    @Override
    public List<ProductInfo> findList(List<String> productIdList) {
        return productInfoRepository.findByProductIdIn(productIdList);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO:cartDTOList){
            Optional<ProductInfo> productInfoOptional =  productInfoRepository.findById(cartDTO.getProductId());
            //判断商品是否存在
            if(!productInfoOptional.isPresent()){
                throw new ProductException(EnumResult.PRODUCT_NOT_EXIST);
            }

            ProductInfo productInfo = productInfoOptional.get();
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if(result < 0){
                //库存不足
                throw new ProductException(EnumResult.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }
    }
}
