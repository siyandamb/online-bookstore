package com.siyanda.bookstore.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private double totalPrice;

    public void calculateTotalPrice() {
        if (book != null && quantity > 0) {
            this.totalPrice = book.getPrice() * quantity;
        } else {
            this.totalPrice = 0;
        }
    }
}