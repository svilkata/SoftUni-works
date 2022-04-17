package com.soft_uni.jsonprocessing.service.interfaces;

import com.soft_uni.jsonprocessing.model.dto.ProductDto.ProductNameAndPriceDto;
import com.soft_uni.jsonprocessing.model.dto.ProductDto.ProductNamePriceAndSellerDto;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void seedProducts() throws IOException;

    List<ProductNamePriceAndSellerDto> findALLProductsWithPriceBetween(BigDecimal lower, BigDecimal upper);

    List<ProductNameAndPriceDto> getProductsBySellerIdAndBuyerNotNull(long id);
}