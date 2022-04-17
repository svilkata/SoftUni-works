package com.example.xml_exc.productsshop.services;

import com.example.xml_exc.productsshop.entities.products.Product;
import com.example.xml_exc.productsshop.entities.products.ExportProductsInRangeDTO;
import com.example.xml_exc.productsshop.entities.products.ProductWithAttributesDTO;
import com.example.xml_exc.productsshop.entities.users.User;
import com.example.xml_exc.productsshop.repositories.ProductRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper mapper;
    private final TypeMap<Product, ProductWithAttributesDTO> productToDtoMapping;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.mapper = new ModelMapper();

        //seller -> firstName + lastName
        //от User на Стринг
        Converter<User, String> userToFullNameConverter = context ->
                context.getSource() == null ? null : context.getSource().getFullName();

        TypeMap<Product, ProductWithAttributesDTO> typeMap = this.mapper.createTypeMap(Product.class, ProductWithAttributesDTO.class);

        // <V> void map(SourceGetter<S> sourceGetter, DestinationSetter<D, V> destinationSetter);
        this.productToDtoMapping = typeMap.addMappings(m -> m
//                .when(Objects::nonNull)
                .using(userToFullNameConverter)
//                .map(Product::getSeller, ProductWithAttributesDTO::setSeller));
                .map(Product::getSeller, ((destination, value) -> destination.setSeller((String) value))));

        this.mapper.addConverter(userToFullNameConverter);
    }

    @Override
    public ExportProductsInRangeDTO getInRange(float from, float to) {
        BigDecimal rangeFrom = BigDecimal.valueOf(from);
        BigDecimal rangeTo = BigDecimal.valueOf(to);

        List<Product> products = this.productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPriceDesc(rangeFrom, rangeTo);

        List<ProductWithAttributesDTO> dtos = products
                .stream()
                .map(this.productToDtoMapping::map)
                .collect(Collectors.toList());

        return new ExportProductsInRangeDTO(dtos);
    }
}
