package com.psd.filter.map.dto;

import com.psd.filter.entity.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductSummaryDTO {
    private Long id;

    private String name;

    private Integer amount;

    private BigDecimal unitaryValue;

    private ProductType productType;
}
