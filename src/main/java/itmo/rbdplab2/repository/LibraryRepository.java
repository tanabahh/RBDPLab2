package itmo.rbdplab2.repository;

import itmo.rbdplab2.model.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository extends CrudRepository<Book, Long>, JpaRepository<Book, Long> {
    List<Book> findByAuthorNameContainsIgnoreCase(String name);
    List<Book> findByGenresContainingIgnoreCase(String name);
    List<Book> findByAuthorName(String author);
    List<Book> findByAnnotationContainingIgnoreCase(String value);
    List<Book> findByNameContainingIgnoreCase(String value);
}
