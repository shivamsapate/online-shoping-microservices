package com.shivam.microservices.order.service;

import com.shivam.microservices.order.dto.OrderRequest;
import com.shivam.microservices.order.model.Order;
import com.shivam.microservices.order.model.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest) {
        //map order request to order object
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setPrice(orderRequest.price());
        order.setQuantity(orderRequest.quantity());
        order.setSkuCode(orderRequest.skuCode());


        orderRepository.save(order);

    }
}
