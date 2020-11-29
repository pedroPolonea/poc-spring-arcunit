package com.psd.filter.service.impl;

import com.psd.filter.entity.ProductEntity;
import com.psd.filter.map.dto.ProductDTO;
import com.psd.filter.map.mapping.ProductMapping;
import com.psd.filter.repository.ProductRepository;
import com.psd.filter.service.ProductService;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapping productMapping;


    @Override
    public Optional<ProductEntity> getId(Long id) {
        log.info("M=ProductServiceImpl.getId, id={}", id);
        return productRepository.findById(id);
    }

    @Override
    public ProductDTO save(final ProductDTO productDTO) {
        log.info("M=ProductServiceImpl.save, productDTO={}", productDTO);

        ProductEntity productEntity = productMapping.productDTOToProductEntity(productDTO);
        productEntity = productRepository.save(productEntity);

        log.info("M=ProductServiceImpl.save, productEntity={}", productEntity);
        return productMapping.productEntityToProductDTO(productEntity);
    }

    @Override
    public ProductDTO update(final Optional<ProductDTO> productDTO) throws Exception {
        validateUpdate(productDTO);

        log.info("M=ProductServiceImpl.update, productDTO={}", productDTO.get());
        ProductEntity productEntity = productMapping.productDTOToProductEntity(productDTO.get());
        productEntity = productRepository.save(productEntity);
        log.info("M=ProductServiceImpl.update, productEntity={}", productEntity);

        return productMapping.productEntityToProductDTO(productEntity);
    }

    private void validateUpdate(final Optional<ProductDTO> productDTO) throws Exception {
        if (!productDTO.map(dto -> dto.getId()).isPresent()) {
            log.error("M=ProductServiceImpl.validateUpdate, I=O productDTO se encontra null ou sem id definido.");
            throw new Exception("Problema na criação do Statement");
        }
    }

    public void delete(final Optional<Long> id) throws NotFoundException {

        final Optional<ProductEntity> productEntity = id.map(idProduct -> productRepository.findById(idProduct))
                .orElseThrow(() -> new NotFoundException("Informe um produto valido."));

        if(productEntity.isPresent()){
            productRepository.delete(productEntity.get());
        } else {
            log.info("M=ProductServiceImpl.delete, I=Recurso não encontrado.");
            throw new NotFoundException("Recurso não encontrado.");
        }
    }



}
