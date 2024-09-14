//package az.company.onlinelibrarysystem.controller;
//
//import az.company.onlinelibrarysystem.entity.Author;
//import az.company.onlinelibrarysystem.entity.Book;
//import az.company.onlinelibrarysystem.repository.AuthorRepository;
//import az.company.onlinelibrarysystem.repository.BookRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//
//@Component
//@RequiredArgsConstructor
//public class BookDataInitializer implements CommandLineRunner {
//
//    private final BookRepository bookRepository;
//    private final AuthorRepository authorRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//        // Create authors
//        Author author1 = new Author();
//        author1.setName("J.K. Rowling");
//        author1.setBiography("Author of the Harry Potter series.");
//
//        Author author2 = new Author();
//        author2.setName("George R.R. Martin");
//        author2.setBiography("Author of A Song of Ice and Fire.");
//
//        // Create books
//        Book book1 = new Book();
//        book1.setTitle("Harry Potter and the Philosopher's Stone");
//        book1.setIsbn("9780747532743");
//        book1.setYearPublished(1997);
//        book1.setCategory("Fantasy");
//        book1.setLanguage("English");
//        book1.setAuthors(Arrays.asList(author1));
//
//        Book book2 = new Book();
//        book2.setTitle("Harry Potter and the Chamber of Secrets");
//        book2.setIsbn("9780747538493");
//        book2.setYearPublished(1998);
//        book2.setCategory("Fantasy");
//        book2.setLanguage("English");
//        book2.setAuthors(Arrays.asList(author1));
//
//        Book book3 = new Book();
//        book3.setTitle("A Game of Thrones");
//        book3.setIsbn("9780553103540");
//        book3.setYearPublished(1996);
//        book3.setCategory("Fantasy");
//        book3.setLanguage("English");
//        book3.setAuthors(Arrays.asList(author2));
//
//        // Save books first
//        bookRepository.saveAll(Arrays.asList(book1, book2, book3));
//
//        // Set the books in the authors
//        author1.setBooks(Arrays.asList(book1, book2));
//        author2.setBooks(Arrays.asList(book3));
//
//        // Save authors
//        authorRepository.saveAll(Arrays.asList(author1, author2));
//    }
//
//}
//
