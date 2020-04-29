package com.huo.service.impl;

import com.huo.dto.CartDTO;
import com.huo.entity.ProductInfo;
import com.huo.enums.ProductStatusEnum;
import com.huo.enums.ResultEnmu;
import com.huo.respository.ProductInfoRepository;
import com.huo.sellException.SellException;
import com.huo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Author: Huo
 * @Description:
 * @Date: Create in 12:19 2020/4/23
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoRepository productInfoRepository;
    @Override
    public ProductInfo findOne(String productId) {
        return productInfoRepository.findOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOSList) {

    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOSList) {
        for (CartDTO cartDTO:cartDTOSList){
            ProductInfo productInfo = productInfoRepository.findOne(cartDTO.getProductId());
            if (productInfo == null){
                throw new SellException(ResultEnmu.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock()-cartDTO.getProductQuantity();
            if (result < 0){
                throw  new SellException(ResultEnmu.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }
    }
}
