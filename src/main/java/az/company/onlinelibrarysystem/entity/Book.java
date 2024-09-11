package az.company.onlinelibrarysystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "books",schema = "library")
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private String publishedDate;
    private String language;
    private String description;


}
