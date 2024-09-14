package az.company.onlinelibrarysystem.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users",schema = "library")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;  // Enum for user roles (ADMIN, USER)

    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "user")
    private List<Rating> ratings;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews;

    // Getters and setters
}

