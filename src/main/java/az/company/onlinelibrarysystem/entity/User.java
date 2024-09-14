package az.company.onlinelibrarysystem.entity;

import az.company.onlinelibrarysystem.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "users",schema = "library")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;

    private Integer status=1;

    @Enumerated(EnumType.STRING)
    private Role role;  // Enum for user roles (ADMIN, USER)

    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "user")
    private List<Rating> ratings;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews;

}

