package com.siyanda.bookstore.controller;

import com.siyanda.bookstore.model.Book;
import com.siyanda.bookstore.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllBooks_ShouldReturnListOfBooks() {
        Book book1 = new Book("978-0134685991", "Effective Java", "Joshua Bloch", 45.00, 10);
        Book book2 = new Book("978-0136083238", "Clean Code", "Robert C. Martin", 40.00, 5);
        List<Book> books = Arrays.asList(book1, book2);

        when(bookService.findAllBooks()).thenReturn(books);

        ResponseEntity<List<Book>> responseEntity = bookController.getAllBooks();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(2, responseEntity.getBody().size());
        verify(bookService, times(1)).findAllBooks();
    }

    @Test
    void getBookByIsbn_ShouldReturnBookWhenExists() {
        Book book = new Book("978-0134685991", "Effective Java", "Joshua Bloch", 45.00, 10);
        when(bookService.findBookByIsbn("978-0134685991")).thenReturn(Optional.of(book));

        ResponseEntity<Book> responseEntity = bookController.getBookByIsbn("978-0134685991");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(book.getTitle(), responseEntity.getBody().getTitle());
        verify(bookService, times(1)).findBookByIsbn("978-0134685991");
    }

    @Test
    void getBookByIsbn_ShouldReturnNotFoundWhenDoesNotExist() {
        when(bookService.findBookByIsbn("978-1234567890")).thenReturn(Optional.empty());

        ResponseEntity<Book> responseEntity = bookController.getBookByIsbn("978-1234567890");

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(bookService, times(1)).findBookByIsbn("978-1234567890");
    }

    @Test
    void addNewBook_ShouldReturnCreatedBook() {
        Book newBook = new Book("978-1617294945", "Spring in Action", "Craig Walls", 30.00, 8);
        when(bookService.addBook(any(Book.class))).thenReturn(newBook);

        ResponseEntity<Book> responseEntity = bookController.addNewBook(newBook);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(newBook.getTitle(), responseEntity.getBody().getTitle());
        verify(bookService, times(1)).addBook(newBook);
    }
}