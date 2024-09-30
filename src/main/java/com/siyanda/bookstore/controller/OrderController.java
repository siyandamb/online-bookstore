package com.siyanda.bookstore.controller;

import com.siyanda.bookstore.model.Book;
import com.siyanda.bookstore.model.Order;
import com.siyanda.bookstore.service.BookService;
import com.siyanda.bookstore.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final BookService bookService;

    public OrderController(OrderService orderService, BookService bookService) {
        this.orderService = orderService;
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestParam String isbn, @RequestParam int quantity) {
        if (quantity <= 0) {
            return ResponseEntity.badRequest().body(null);
        }

        Optional<Book> bookOptional = bookService.findBookByIsbn(isbn);
        if (bookOptional.isPresent()) {
            Order order = orderService.placeOrder(bookOptional.get(), quantity);
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

}