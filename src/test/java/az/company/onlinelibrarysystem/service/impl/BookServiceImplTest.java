package az.company.onlinelibrarysystem.service.impl;

import az.company.onlinelibrarysystem.dto.request.BookRequest;
import az.company.onlinelibrarysystem.dto.response.BookResponse;
import az.company.onlinelibrarysystem.entity.Author;
import az.company.onlinelibrarysystem.entity.Book;
import az.company.onlinelibrarysystem.exception.CustomException;
import az.company.onlinelibrarysystem.repository.AuthorRepository;
import az.company.onlinelibrarysystem.repository.BookRepository;
import az.company.onlinelibrarysystem.service.impl.BookServiceImpl;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

public class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddBook() {
        // Arrange
        BookRequest request = new BookRequest();
        request.setTitle("Book Title");
        request.setIsbn("1234567890");
        request.setYearPublished(2023);
        request.setLanguage("English");
        request.setCategory("Fiction");
        request.setAuthorIds(Arrays.asList(1L, 2L));

        Author author1 = new Author();
        author1.setId(1L);
        Author author2 = new Author();
        author2.setId(2L);

        Book book = new Book();
        book.setTitle("Book Title");
        book.setIsbn("1234567890");
        book.setYearPublished(2023);
        book.setLanguage("English");
        book.setCategory("Fiction");
        book.setAuthors(Arrays.asList(author1, author2));

        when(authorRepository.findAllById(anyList())).thenReturn(Arrays.asList(author1, author2));
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        when(authorRepository.saveAll(anyList())).thenReturn(Arrays.asList(author1, author2));

        // Act
        BookResponse response = bookService.addBook(request);

        // Assert
        assertNotNull(response);
        assertEquals("Book Title", response.getTitle());
        assertEquals("1234567890", response.getIsbn());
        assertEquals(2023, response.getYearPublished());
        assertEquals("English", response.getLanguage());
        assertEquals("Fiction", response.getCategory());
        assertEquals(2, response.getAuthorNames().size());
    }

    @Test
    void testUpdateBook() {
        // Arrange
        BookRequest request = new BookRequest();
        request.setTitle("Updated Title");
        request.setIsbn("0987654321");
        request.setYearPublished(2024);
        request.setLanguage("Spanish");
        request.setCategory("Non-Fiction");
        request.setAuthorIds(Arrays.asList(1L, 3L));

        Book existingBook = new Book();
        existingBook.setId(1L);
        existingBook.setTitle("Old Title");
        existingBook.setIsbn("1234567890");
        existingBook.setYearPublished(2023);
        existingBook.setLanguage("English");
        existingBook.setCategory("Fiction");

        Author author1 = new Author();
        author1.setId(1L);
        Author author3 = new Author();
        author3.setId(3L);

        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(existingBook));
        when(authorRepository.findAllById(anyList())).thenReturn(Arrays.asList(author1, author3));
        when(bookRepository.save(any(Book.class))).thenReturn(existingBook);

        // Act
        BookResponse response = bookService.updateBook(1L, request);

        // Assert
        assertNotNull(response);
        assertEquals("Updated Title", response.getTitle());
        assertEquals("0987654321", response.getIsbn());
        assertEquals(2024, response.getYearPublished());
        assertEquals("Spanish", response.getLanguage());
        assertEquals("Non-Fiction", response.getCategory());
        assertEquals(2, response.getAuthorNames().size());
    }

    @Test
    void testUpdateBook_ThrowsException_WhenBookNotFound() {
        // Arrange
        BookRequest request = new BookRequest();
        request.setTitle("Updated Title");

        when(bookRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act & Assert
        CustomException thrown = assertThrows(CustomException.class, () ->
                bookService.updateBook(1L, request)
        );
        assertEquals("Book not found with id 1", thrown.getMessage());
    }

    @Test
    void testDeleteBook() {
        // Arrange
        Book book = new Book();
        book.setId(1L);

        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));

        // Act
        bookService.deleteBook(1L);

        // Assert
        verify(bookRepository, times(1)).delete(book);
    }

    @Test
    void testDeleteBook_ThrowsException_WhenBookNotFound() {
        // Arrange
        when(bookRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act & Assert
        CustomException thrown = assertThrows(CustomException.class, () ->
                bookService.deleteBook(1L)
        );
        assertEquals("Book not found with id 1", thrown.getMessage());
    }

    @Test
    void testGetBookById() {
        // Arrange
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Book Title");

        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));

        // Act
        BookResponse response = bookService.getBookById(1L);

        // Assert
        assertNotNull(response);
        assertEquals("Book Title", response.getTitle());
    }

    @Test
    void testGetBookById_ThrowsException_WhenBookNotFound() {
        // Arrange
        when(bookRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act & Assert
        CustomException thrown = assertThrows(CustomException.class, () ->
                bookService.getBookById(1L)
        );
        assertEquals("Book not found with id 1", thrown.getMessage());
    }

    @Test
    void testGetAllBooks() {
        // Arrange
        Book book1 = new Book();
        book1.setTitle("Book Title 1");

        Book book2 = new Book();
        book2.setTitle("Book Title 2");

        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

        // Act
        List<BookResponse> responses = bookService.getAllBooks();

        // Assert
        assertNotNull(responses);
        assertEquals(2, responses.size());
        assertEquals("Book Title 1", responses.get(0).getTitle());
        assertEquals("Book Title 2", responses.get(1).getTitle());
    }

    @Test
    void testSearchBooks() {
        // Arrange
        Book book1 = new Book();
        book1.setTitle("Searchable Book");
        book1.setIsbn("12345");

        Book book2 = new Book();
        book2.setTitle("Another Book");
        book2.setIsbn("67890");

        when(bookRepository.findByTitleContainingOrIsbnContaining(anyString(), anyString()))
                .thenReturn(Arrays.asList(book1, book2));

        // Act
        List<BookResponse> responses = bookService.searchBooks("Searchable");

        // Assert
        assertNotNull(responses);
        assertEquals(1, responses.size());
        assertEquals("Searchable Book", responses.get(0).getTitle());
    }

    @Test
    void testFilterBooks() {
        // Arrange
        Book book = new Book();
        book.setTitle("Filtered Book");
        book.setCategory("Fiction");

        when(bookRepository.filterBooks(anyString(), anyString(), any())).thenReturn(Arrays.asList(book));

        // Act
        List<BookResponse> responses = bookService.filterBooks("Fiction", null, null);

        // Assert
        assertNotNull(responses);
        assertEquals(1, responses.size());
        assertEquals("Filtered Book", responses.get(0).getTitle());
    }

    @Test
    void testGetBooksByAuthor() {
        // Arrange
        Author author = new Author();
        author.setId(1L);

        Book book = new Book();
        book.setTitle("Book by Author");

        when(authorRepository.findById(anyLong())).thenReturn(Optional.of(author));
        when(bookRepository.findBooksByAuthorsContains(any(Author.class)))
                .thenReturn(Arrays.asList(book));

        // Act
        List<BookResponse> responses = bookService.getBooksByAuthor(1L);

        // Assert
        assertNotNull(responses);
        assertEquals(1, responses.size());
        assertEquals("Book by Author", responses.get(0).getTitle());
    }

    @Test
    void testGetBooksByAuthor_ThrowsException_WhenAuthorNotFound() {
        // Arrange
        when(authorRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act & Assert
        CustomException thrown = assertThrows(CustomException.class, () ->
                bookService.getBooksByAuthor(1L)
        );
        assertEquals("Author not found with id 1", thrown.getMessage());
    }
}
