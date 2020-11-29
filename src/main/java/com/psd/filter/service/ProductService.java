package com.psd.filter.service;

import com.psd.filter.entity.ProductEntity;
import com.psd.filter.map.dto.ProductDTO;
import javassist.NotFoundException;

import java.util.Optional;

public interface ProductService {

    Optional<ProductEntity> getId(Long id);

    ProductDTO save(ProductDTO productDTO);

    ProductDTO update(Optional<ProductDTO> productDTO) throws Exception;

    void delete(final Optional<Long> id) throws NotFoundException;

}
