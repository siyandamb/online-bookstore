package com.siyanda.bookstore.service;

import com.siyanda.bookstore.model.Book;
import com.siyanda.bookstore.model.Order;
import com.siyanda.bookstore.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void placeOrder_ShouldCreateOrderSuccessfully() {
        Book book = new Book("978-0134685991", "Effective Java", "Joshua Bloch", 45.00, 10);
        int quantity = 2;

        Order order = new Order();
        order.setBook(book);
        order.setQuantity(quantity);
        order.calculateTotalPrice();

        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order createdOrder = orderService.placeOrder(book, quantity);

        assertNotNull(createdOrder);
        assertEquals(90.00, createdOrder.getTotalPrice(), 0.01);
        assertEquals(2, createdOrder.getQuantity());
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void placeOrder_ShouldThrowExceptionWhenBookIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            orderService.placeOrder(null, 2);
        });
        assertEquals("Book cannot be null", exception.getMessage());
    }

    @Test
    void placeOrder_ShouldThrowExceptionWhenQuantityIsZero() {
        Book book = new Book("978-0134685991", "Effective Java", "Joshua Bloch", 45.00, 10);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            orderService.placeOrder(book, 0);
        });
        assertEquals("Quantity must be greater than zero", exception.getMessage());
    }

    @Test
    void placeOrder_ShouldThrowExceptionWhenQuantityExceedsStock() {
        Book book = new Book("978-0134685991", "Effective Java", "Joshua Bloch", 45.00, 1);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            orderService.placeOrder(book, 2);
        });
        assertEquals("Insufficient stock available", exception.getMessage());
    }
}
