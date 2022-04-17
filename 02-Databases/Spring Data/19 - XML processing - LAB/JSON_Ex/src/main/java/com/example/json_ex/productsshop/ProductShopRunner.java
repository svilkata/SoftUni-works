package com.example.json_ex.productsshop;

import com.example.json_ex.productsshop.entities.categories.CategoryStatsDTO;
import com.example.json_ex.productsshop.entities.categories.XMLCategoryStatsDTO;
import com.example.json_ex.productsshop.entities.categories.XMLCategoryStatsList;
import com.example.json_ex.productsshop.entities.products.ProductWithoutBuyerDTO;
import com.example.json_ex.productsshop.entities.users.User;
import com.example.json_ex.productsshop.entities.users.UserWithSoldProductsDTO;
import com.example.json_ex.productsshop.services.ProductService;
import com.example.json_ex.productsshop.services.SeedService;
import com.example.json_ex.productsshop.services.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductShopRunner implements CommandLineRunner {
    private final SeedService seedService;
    private final ProductService productService;
    private UserService userService;

    private final Gson gson;


    @Autowired
    public ProductShopRunner(SeedService seedService, ProductService productService, UserService userService) {
        this.seedService = seedService;
        this.productService = productService;
        this.userService = userService;
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    @Override
    public void run(String... args) throws Exception {
//        this.seedService.seedAll();
//        _01_Query_ProductsInRange();
//        _02_Query_SuccessfullySoldProducts();
//        _03_Query_CategoriesByProductsCount();
//        _04_Query_UsersAndProducts();
//        getCategoryStats();
//        xmlMarshallDemo();
        xmlDemo();


        /**
         *   {
         *     "category": "Drugs",
         *     "productsCount": 79,
         *     "averagePrice": 862.052911,
         *     "totalRevenue": 68102.18
         *   }
         *
         *   <categories-stats>
         *       <category>
         *           <name>Drugs</name>
         *           <productsCount>79</productsCount>
         *           <averagePrice>836</averagePrice>
         *           <totalRevenue>56912.80</totalRevenue>
         *       </category>
         *   </categories-stats>
         */


    }

    private void getCategoryStats() {
        List<CategoryStatsDTO> result = this.productService.getCategoryStatistics();
        String json = this.gson.toJson(result);
        System.out.println(json);
    }

    private void xmlDemo() throws JAXBException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "      <category>\n" +
                "         <name>Drugs</name>\n" +
                "         <productsCount>79</productsCount>\n" +
                "         <averagePrice>836</averagePrice>\n" +
                "         <totalRevenue>56912.80</totalRevenue>\n" +
                "       </category>";

        JAXBContext context = JAXBContext.newInstance(XMLCategoryStatsDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

//        InputStream inputStream = getClass().getResourceAsStream("/files/input/xml/address.xml");
//        BufferedReader bfr = new BufferedReader(new InputStreamReader(inputStream));

        ByteArrayInputStream inputStream = new ByteArrayInputStream(xml.getBytes());
        XMLCategoryStatsDTO result = (XMLCategoryStatsDTO) unmarshaller.unmarshal(inputStream);

        System.out.println(result);
    }

    private void xmlMarshallDemo() throws JAXBException {
        List<XMLCategoryStatsDTO> xmlResult = this.productService.getCategoryStatistics()
                .stream()
                .map(XMLCategoryStatsDTO::new)
                .collect(Collectors.toList());

        XMLCategoryStatsList xmlCategoryStatsList = new XMLCategoryStatsList(xmlResult);

        JAXBContext context = JAXBContext.newInstance(XMLCategoryStatsList.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(xmlCategoryStatsList, System.out);
    }

    private void _01_Query_ProductsInRange() {
        List<ProductWithoutBuyerDTO> productsForSell = this.productService.getProductsInPriceRangeForSell(500.0f, 1000.0f);
        String jsonContent = this.gson.toJson(productsForSell);
        System.out.println(jsonContent);
    }

    private void _02_Query_SuccessfullySoldProducts() {
        List<UserWithSoldProductsDTO> usersWithSoldProducts = this.userService.getUsersWithSoldProducts();
        String jsonContent = this.gson.toJson(usersWithSoldProducts);
        System.out.println(jsonContent);
    }

    private void _03_Query_CategoriesByProductsCount() {
        List<CategoryStatsDTO> categoryStatistics = this.productService.getCategoryStatistics();
        String jsonContent = this.gson.toJson(categoryStatistics);
        System.out.println(jsonContent);
    }

    private void _04_Query_UsersAndProducts() {
        List<User> output = this.userService.getUsersWithSoldProductsOrderedByCount();
        int a = 5;
        String jsonContent = this.gson.toJson(output);
        System.out.println(jsonContent);
    }
}
