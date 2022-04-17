package com.example.xml_exc.productsshop.services;

import com.example.xml_exc.productsshop.entities.users.ExportSellersDTO;

public interface UserService {
    ExportSellersDTO findAllWithSoldProducts();
}
