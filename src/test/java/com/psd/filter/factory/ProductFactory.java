package com.psd.filter.factory;

import com.psd.filter.entity.ProductEntity;
import com.psd.filter.entity.enums.ProductType;
import com.psd.filter.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductFactory {

    @Autowired
    private ProductRepository productRepository;

    public ProductEntity createProductBase(){
        return productRepository.save(ProductEntity.builder()
                .name("TV")
                .description("TV Sony")
                .active(true)
                .productType(ProductType.ELECTRONICS)
                .unitaryValue(BigDecimal.ONE)
                .acquisitionValue(BigDecimal.TEN)
                .amount(19)
                .build());
    }


}
