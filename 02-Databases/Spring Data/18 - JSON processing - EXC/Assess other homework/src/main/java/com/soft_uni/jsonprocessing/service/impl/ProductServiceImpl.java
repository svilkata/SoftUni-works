package com.soft_uni.jsonprocessing.service.impl;

import com.google.gson.Gson;
import com.soft_uni.jsonprocessing.model.dto.ProductDto.ProductNameAndPriceDto;
import com.soft_uni.jsonprocessing.model.dto.ProductDto.ProductNamePriceAndSellerDto;
import com.soft_uni.jsonprocessing.model.dto.ProductDto.ProductSeedDto;
import com.soft_uni.jsonprocessing.model.entity.Product;
import com.soft_uni.jsonprocessing.repository.ProductRepository;
import com.soft_uni.jsonprocessing.service.interfaces.CategoryService;
import com.soft_uni.jsonprocessing.service.interfaces.ProductService;
import com.soft_uni.jsonprocessing.service.interfaces.UserService;
import com.soft_uni.jsonprocessing.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static com.soft_uni.jsonprocessing.constants.Constants.RESOURCE_FILE_PATH;

@Service
public class ProductServiceImpl implements ProductService {
    private static final String JSON_FILE_PATH = "products.json";

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final UserService userService;
    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository,
                              ModelMapper modelMapper, Gson gson,
                              ValidationUtil validationUtil,
                              UserService userService,
                              CategoryService categoryService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedProducts() throws IOException {
        if (productRepository.count() > 0) {
            return;
        }
        String productContent = Files.readString(Path.of(RESOURCE_FILE_PATH + JSON_FILE_PATH));
        ProductSeedDto[] productSeedDtos = gson
                .fromJson(productContent, ProductSeedDto[].class);
        Arrays.stream(productSeedDtos)
                .filter(validationUtil::isValid)
                .map(productSeedDto -> {
                    Product product = modelMapper.map(productSeedDto, Product.class);
                    product.setSeller(userService.findRandomUser());
                    product.setCategories(categoryService.findRandomCategories());
                    if (ThreadLocalRandom.current().nextBoolean()) {
                        product.setBuyer(userService.findRandomUser());
                    }
                    return product;
                })
                .forEach(productRepository::save);


    }

    @Override
    public List<ProductNamePriceAndSellerDto> findALLProductsWithPriceBetween(BigDecimal lower, BigDecimal upper) {
        return productRepository
                .findAllByPriceBetweenAndBuyerIsNullOrderByPriceAsc(lower, upper)
                .stream()
                .map(product -> {
                    ProductNamePriceAndSellerDto productDto = modelMapper.map(product, ProductNamePriceAndSellerDto.class);
                    productDto.setSeller(String.format("%s %s",
                            product.getSeller().getFirstName(),
                            product.getSeller().getLastName()));
                    return productDto;
                })
                .collect(Collectors.toList());

    }

    @Override
    public List<ProductNameAndPriceDto> getProductsBySellerIdAndBuyerNotNull(long id) {
        return productRepository.findAllBySellerIdAndBuyerIsNotNull(id)
                .stream()
                .map(product -> modelMapper.map(product, ProductNameAndPriceDto.class))
                .collect(Collectors.toList());
    }


}
