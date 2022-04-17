package com.example.xml_exc.productsshop.services;

import com.example.xml_exc.productsshop.entities.categories.Category;
import com.example.xml_exc.productsshop.entities.categories.CategoriesImportDTO;
import com.example.xml_exc.productsshop.entities.products.Product;
import com.example.xml_exc.productsshop.entities.products.ProductsImportDTO;
import com.example.xml_exc.productsshop.entities.users.User;
import com.example.xml_exc.productsshop.entities.users.UsersImportDTO;
import com.example.xml_exc.productsshop.repositories.CategoryRepository;
import com.example.xml_exc.productsshop.repositories.ProductRepository;
import com.example.xml_exc.productsshop.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SeedServiceImpl implements SeedService {
    private static final Path USERS_XML_PATH =
            Path.of("src", "main", "resources", "productsshopInit", "users.xml");
    private static final Path CATEGORIES_XML_PATH =
            Path.of("src", "main", "resources", "productsshopInit", "categories.xml");
    private static final Path PRODUCTS_XML_PATH =
            Path.of("src", "main", "resources", "productsshopInit", "products.xml");
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private ProductRepository productRepository;
    ModelMapper modelMapper;


    @Autowired
    public SeedServiceImpl(UserRepository userRepository, CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;

        modelMapper = new ModelMapper();
    }

    @Override
    public void seedUsers() throws IOException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(UsersImportDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        BufferedReader xmlReader = Files.newBufferedReader(USERS_XML_PATH);
        UsersImportDTO usersDTO = (UsersImportDTO) unmarshaller.unmarshal(xmlReader);

        List<User> entities = usersDTO.getUsers()
                .stream()
                .map(dto -> this.modelMapper.map(dto, User.class))
                .collect(Collectors.toList());

        this.userRepository.saveAll(entities);
    }

    @Override
    public void seedCategories() throws FileNotFoundException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(CategoriesImportDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        FileReader xmlReader = new FileReader(CATEGORIES_XML_PATH.toAbsolutePath().toString());
        CategoriesImportDTO importDTO = (CategoriesImportDTO) unmarshaller.unmarshal(xmlReader);

        List<Category> entities = importDTO
                .getCategories()
                .stream()
                .map(catName -> new Category(catName.getName()))
                .collect(Collectors.toList());

        this.categoryRepository.saveAll(entities);
    }

    @Override
    public void seedProducts() throws FileNotFoundException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(ProductsImportDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        FileReader xmlReader = new FileReader(PRODUCTS_XML_PATH.toAbsolutePath().toString());
        ProductsImportDTO importDTOs = (ProductsImportDTO) unmarshaller.unmarshal(xmlReader);

        List<Product> entities = importDTOs
                .getProducts()
                .stream()
                .map(dto -> new Product(dto.getName(), dto.getPrice()))
                .map(this::sendRandomcategories)
                .map(this::setRandomSeller)
                .map(this::setRandomBuyer)
                .collect(Collectors.toList());

        this.productRepository.saveAll(entities);
    }

    private Product sendRandomcategories(Product product) {
        Random random = new Random();
        long categoriesDbCount = this.categoryRepository.count();

        int count = random.nextInt((int) categoriesDbCount) + 1;

        Set<Category> categories = new HashSet<>();
        for (int i = 0; i < count; i++) {
            int randomId = random.nextInt((int) categoriesDbCount) + 1;

            Optional<Category> randomCategory = this.categoryRepository.findById(randomId);
            categories.add(randomCategory.get());
        }

        product.setCategories(categories);
        return product;
    }

    private Product setRandomSeller(Product product) {
        //продавач винаги има
        Optional<User> seller = getRandomUser();
        product.setSeller(seller.get());
        return product;
    }

    private Product setRandomBuyer(Product product) {
        //купувач не винаги има
        if (product.getPrice().compareTo(BigDecimal.valueOf(944)) > 0) {
            return product;
        }

        Optional<User> buyer = getRandomUser();
        product.setBuyer(buyer.get());
        return product;
    }

    private Optional<User> getRandomUser() {
        long usersCount = this.userRepository.count();
        int randomUserId = new Random().nextInt((int) usersCount) + 1;
        Optional<User> seller = this.userRepository.findById(randomUserId);
        return seller;
    }
}
