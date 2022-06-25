package com.example.shoping.service;

import com.example.shoping.models.dtos.binding.ProductAddBindingDto;
import com.example.shoping.models.dtos.view.ProductViewDto;
import com.example.shoping.models.entities.CategoryEntity;
import com.example.shoping.models.entities.ProductEntity;
import com.example.shoping.models.entities.UserEntity;
import com.example.shoping.models.entities.enums.CategoryName;
import com.example.shoping.repositories.CategoryRepository;
import com.example.shoping.repositories.ProductRepository;
import com.example.shoping.repositories.UserRepository;
import com.example.shoping.security.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, UserRepository userRepository, LoggedUser loggedUser, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.modelMapper = modelMapper;
    }

    public boolean createProduct(ProductAddBindingDto productAddBindingDto) {
        Optional<ProductEntity> productByNameOpt = this.productRepository.findByName(productAddBindingDto.getName());

        if (productByNameOpt.isPresent()) {
            return false;
        }

        Optional<CategoryEntity> category = this.categoryRepository.findByName(productAddBindingDto.getCategory());

        Optional<UserEntity> owner = this.userRepository.findById(this.loggedUser.getId());

        ProductEntity productToAdd = new ProductEntity();
        productToAdd.setName(productAddBindingDto.getName());
        productToAdd.setDescription(productAddBindingDto.getDescription());
        productToAdd.setPrice(productAddBindingDto.getPrice());
        productToAdd.setNeededBefore(productAddBindingDto.getNeededBefore());
        productToAdd.setCategory(category.get());
        productToAdd.setUser(owner.get());

        this.productRepository.save(productToAdd);

        return true;
    }

    public BigDecimal getTotalSum(){
        return this.productRepository.findTotalProductsPriceSum();
    }

    public List<ProductViewDto> findAllProductsByCategoryName(CategoryName categoryName) {
        return productRepository.findAllByCategory_Name(categoryName)
                .stream()
                .map(product -> modelMapper.map(product, ProductViewDto.class))
                .collect(Collectors.toList());
    }

    public void buyById(Long id) {
        productRepository.deleteById(id);
    }

    public void buyAll() {
        productRepository.deleteAll();
    }
}
