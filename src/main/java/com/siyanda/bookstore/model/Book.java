package com.siyanda.bookstore.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String isbn;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private double price;

    private int stock;

    public Book(String isbn, String title, String author, double price, int stock) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.price = price;
        this.stock = stock;
    }
}
