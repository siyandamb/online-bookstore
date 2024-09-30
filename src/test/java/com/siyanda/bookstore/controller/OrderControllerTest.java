package com.siyanda.bookstore.controller;

import com.siyanda.bookstore.model.Book;
import com.siyanda.bookstore.model.Order;
import com.siyanda.bookstore.service.BookService;
import com.siyanda.bookstore.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @Mock
    private BookService bookService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void placeOrder_ShouldReturnOrderWhenBookExists() {
        Book book = new Book("978-0134685991", "Effective Java", "Joshua Bloch", 45.00, 10);
        int quantity = 2;

        Order order = new Order();
        order.setBook(book);
        order.setQuantity(quantity);
        order.calculateTotalPrice();

        when(bookService.findBookByIsbn("978-0134685991")).thenReturn(Optional.of(book));
        when(orderService.placeOrder(any(Book.class), anyInt())).thenReturn(order);

        ResponseEntity<Order> responseEntity = orderController.placeOrder("978-0134685991", quantity);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(order.getTotalPrice(), responseEntity.getBody().getTotalPrice());
        verify(bookService, times(1)).findBookByIsbn("978-0134685991");
        verify(orderService, times(1)).placeOrder(book, quantity);
    }

    @Test
    void placeOrder_ShouldReturnBadRequestWhenBookDoesNotExist() {
        int quantity = 2;
        when(bookService.findBookByIsbn("978-0134685991")).thenReturn(Optional.empty());

        ResponseEntity<Order> responseEntity = orderController.placeOrder("978-0134685991", quantity);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
        verify(bookService, times(1)).findBookByIsbn("978-0134685991");
        verify(orderService, never()).placeOrder(any(Book.class), anyInt());
    }

    @Test
    void placeOrder_ShouldReturnBadRequestWhenQuantityIsZero() {
        String isbn = "978-0134685991";
        int quantity = 0;

        when(bookService.findBookByIsbn(isbn)).thenReturn(Optional.of(new Book(isbn, "Effective Java", "Joshua Bloch", 45.00, 10)));

        ResponseEntity<Order> response = orderController.placeOrder(isbn, quantity);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
