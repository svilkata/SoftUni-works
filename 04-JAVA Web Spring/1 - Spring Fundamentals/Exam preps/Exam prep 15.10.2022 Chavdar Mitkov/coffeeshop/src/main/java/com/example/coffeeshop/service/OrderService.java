package com.example.coffeeshop.service;

import com.example.coffeeshop.model.service.OrderServiceModelDTO;
import com.example.coffeeshop.model.view.OrderViewModelDTO;

import java.util.List;

public interface OrderService {
    void addOrder(OrderServiceModelDTO orderServiceModelDTO);

    List<OrderViewModelDTO> findAllOrderOrderByPriceDesc();

    void readyOrder(Long id);
}
