package com.example.xml_exc.productsshop;


import com.example.xml_exc.productsshop.entities.products.ExportProductsInRangeDTO;
import com.example.xml_exc.productsshop.entities.users.ExportSellersDTO;
import com.example.xml_exc.productsshop.services.ProductService;
import com.example.xml_exc.productsshop.services.SeedService;
import com.example.xml_exc.productsshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

@Component
public class ProductShopRunner implements CommandLineRunner {
    private final SeedService seedService;
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public ProductShopRunner(SeedService seedService, ProductService productService, UserService userService) {
        this.seedService = seedService;
        this.productService = productService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
//        this.seedService.seedAll();

//        this._01_productsInRange();
        this._02_findUsersWithSoldProducts();
    }

    private void _02_findUsersWithSoldProducts() throws JAXBException {
        ExportSellersDTO result = this.userService.findAllWithSoldProducts();

        JAXBContext jaxbContext = JAXBContext.newInstance(ExportSellersDTO.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(result, System.out);
    }

    private void _01_productsInRange() throws JAXBException {
        ExportProductsInRangeDTO inRange = this.productService.getInRange(500, 1000);

        JAXBContext jaxbContext = JAXBContext.newInstance(ExportProductsInRangeDTO.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(inRange, System.out);
    }
}
