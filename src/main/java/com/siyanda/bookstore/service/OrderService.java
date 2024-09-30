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
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        if (quantity > book.getStock()) {
            throw new IllegalArgumentException("Insufficient stock available");
        }

        Order order = new Order();
        order.setBook(book);
        order.setQuantity(quantity);
        order.calculateTotalPrice();
        return orderRepository.save(order);
    }
}
