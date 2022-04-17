package com.example.xml_exc.productsshop.services;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface SeedService {
    void seedUsers() throws IOException, JAXBException;

    void seedCategories() throws FileNotFoundException, JAXBException;

    void seedProducts() throws FileNotFoundException, JAXBException;

    default void seedAll() throws IOException, JAXBException {
        seedUsers();
        seedCategories();
        seedProducts();
    }
}
