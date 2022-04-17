package com.example.xml_exc.productsshop.services;

import com.example.xml_exc.productsshop.entities.products.ExportProductsInRangeDTO;

public interface ProductService {

    ExportProductsInRangeDTO getInRange(float from, float to);
}
