package com.example.coffeeshop.service.impl;

import com.example.coffeeshop.model.entity.Order;
import com.example.coffeeshop.model.service.OrderServiceModelDTO;
import com.example.coffeeshop.model.view.OrderViewModelDTO;
import com.example.coffeeshop.repository.OrderRepository;
import com.example.coffeeshop.security.CurrentUser;
import com.example.coffeeshop.service.CategoryService;
import com.example.coffeeshop.service.OrderService;
import com.example.coffeeshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;
    private final UserService userService;
    private final CategoryService categoryService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper, CurrentUser currentUser, UserService userService, CategoryService categoryService) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    public void addOrder(OrderServiceModelDTO orderServiceModelDTO) {
        Order order = modelMapper.map(orderServiceModelDTO, Order.class);
        order.setEmployee(userService.findById(currentUser.getId())); //вземаме кой е user-а от currentUser, а не от базата данни
        order.setCategory(categoryService.findByCategoryNameEnum(orderServiceModelDTO.getCategory())); //превключваме наново от CategoryEnum към Category

        orderRepository.save(order);
    }

    @Override
    public List<OrderViewModelDTO> findAllOrderOrderByPriceDesc() {
        return orderRepository
                .findAll()
                .stream()
                .sorted((f, s) -> s.getPrice().compareTo(f.getPrice()))
                .map(order -> modelMapper.map(order, OrderViewModelDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void readyOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
