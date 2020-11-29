package com.psd.filter.map.mapping;


import com.psd.filter.entity.ProductEntity;
import com.psd.filter.map.dto.ProductSummaryDTO;
import com.psd.filter.map.dto.ProductDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapping {

    ProductDTO productEntityToProductDTO(ProductEntity product);

    ProductEntity productDTOToProductEntity(ProductDTO dto);

    List<ProductSummaryDTO> productEntityToProductSummaryDTO(List<ProductEntity> products);

}
