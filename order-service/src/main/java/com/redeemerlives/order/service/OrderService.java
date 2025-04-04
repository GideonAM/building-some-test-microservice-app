package com.redeemerlives.order.service;

import com.redeemerlives.order.client.InventoryClient;
import com.redeemerlives.order.dto.OrderRequest;
import com.redeemerlives.order.model.Order;
import com.redeemerlives.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    public void placeOrder(OrderRequest orderRequest) {

        boolean inStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());
        if (inStock) {
            Order order = new Order(null,
                    UUID.randomUUID().toString(),
                    orderRequest.skuCode(),
                    orderRequest.price(),
                    orderRequest.quantity());
            orderRepository.save(order);
            return;
        }

        throw new RuntimeException("Product with skuCod: " + orderRequest.skuCode() + " is not in stock");
    }
}
