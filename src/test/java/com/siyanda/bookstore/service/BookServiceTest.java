package com.siyanda.bookstore.service;

import com.siyanda.bookstore.model.Book;
import com.siyanda.bookstore.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Book book1 = new Book("978-0134685991", "Effective Java", "Joshua Bloch", 45.00, 10);
        Book book2 = new Book("978-0136083238", "Clean Code", "Robert C. Martin", 40.00, 5);

        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));
        when(bookRepository.findByIsbn("978-0134685991")).thenReturn(Optional.of(book1));
        when(bookRepository.save(any(Book.class))).thenAnswer(invocation -> invocation.getArgument(0));
    }

    @Test
    void findAllBooks_ShouldReturnListOfBooks() {
        List<Book> books = bookService.findAllBooks();
        assertEquals(2, books.size());
        assertEquals("Effective Java", books.getFirst().getTitle());
    }

    @Test
    void findBookByIsbn_ShouldReturnBookWhenExists() {
        Optional<Book> foundBook = bookService.findBookByIsbn("978-0134685991");
        assertTrue(foundBook.isPresent());
        assertEquals("Effective Java", foundBook.get().getTitle());
    }

    @Test
    void findBookByIsbn_ShouldReturnEmptyWhenNotExists() {
        Optional<Book> foundBook = bookService.findBookByIsbn("978-1234567890");
        assertFalse(foundBook.isPresent());
    }

    @Test
    void addBook_ShouldSaveNewBook() {
        Book book = new Book("978-1617294945", "Spring in Action", "Craig Walls", 30.00, 8);
        Book savedBook = bookService.addBook(book);

        assertNotNull(savedBook);
        assertEquals("Spring in Action", savedBook.getTitle());
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void addBook_ShouldNotSaveBookWithNegativePrice() {
        Book book = new Book("978-1617294945", "Spring in Action", "Craig Walls", -30.00, 8);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bookService.addBook(book);
        });
        assertEquals("Price must be positive", exception.getMessage());
    }
}
