package com.siyanda.bookstore.service;

import com.siyanda.bookstore.model.Book;
import com.siyanda.bookstore.model.Order;
import com.siyanda.bookstore.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order placeOrder(Book book, int quantity) {
        Order order = new Order();
        order.setBook(book);
        order.setQuantity(quantity);
        order.setTotalPrice(book.getPrice() * quantity);
        return orderRepository.save(order);
    }
}