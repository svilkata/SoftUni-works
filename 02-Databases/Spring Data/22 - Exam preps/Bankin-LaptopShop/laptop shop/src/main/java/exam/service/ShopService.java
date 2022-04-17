package exam.service;


import exam.model.entities.Shop;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;


public interface ShopService {

    boolean areImported();

    String readShopsFileContent() throws IOException;

    String importShops() throws JAXBException, FileNotFoundException;

    Optional<Shop> findShopByName(String name);
}
