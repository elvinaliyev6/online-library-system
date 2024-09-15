package az.company.onlinelibrarysystem.repository;

import az.company.onlinelibrarysystem.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental,Long> {

    Long countByActive(boolean active);

    @Query("SELECT r.book.id, r.book.title, COUNT(r) FROM Rental r GROUP BY r.book.id, r.book.title ORDER BY COUNT(r) DESC")
    List<Object[]> findMostReadBooks();

    @Query("SELECT r.user.id, r.user.username, COUNT(r), COUNT(CASE WHEN r.active = true THEN 1 END) FROM Rental r GROUP BY r.user.id, r.user.username")
    List<Object[]> findUserActivityReport();

    List<Rental> findByBookId(Long bookId);
}
