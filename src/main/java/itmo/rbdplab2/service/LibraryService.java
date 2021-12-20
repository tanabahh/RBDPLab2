package itmo.rbdplab2.service;

import itmo.rbdplab2.client.BadRequestException;
import itmo.rbdplab2.model.Book;
import itmo.rbdplab2.repository.LibraryRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class LibraryService {
    private final LibraryRepository libraryRepository;

    @Autowired
    public LibraryService(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    @Retry(name = "test")
    @Transactional
    public Book transactionTest(Boolean flag) throws BadRequestException {
        //Book book = new Book("test", "test", "test", LocalDate.now(), "test", "test");
        Book book = libraryRepository.findByAuthorName("author").get(0);
        if (flag && Objects.equals(book.getIsbn(), "12")) {
            book.setIsbn("21");
            libraryRepository.save(book);
            throw new BadRequestException("haha");
        }
        book.setAnnotation("test_ann6");
        return book;
    }

    public Book save (Book book) {
        return libraryRepository.save(book);
    }

    public List<Book> find(Boolean nameFlag, Boolean authorFlag, Boolean keyWordsFlag, String value) {
        if (nameFlag) {
            return libraryRepository.findByNameContainingIgnoreCase(value);
        }
        if (authorFlag) {
            return libraryRepository.findByAuthorName(value);
        }
        if (keyWordsFlag) {
            Set<Book> books = new HashSet<>();
            String[] values = value.split("%20");
            for(String val: values) {
                books.addAll(libraryRepository.findByNameContainingIgnoreCase(val));
                books.addAll(libraryRepository.findByAuthorNameContainsIgnoreCase(val));
                books.addAll(libraryRepository.findByGenresContainingIgnoreCase(val));
                books.forEach(book -> book.setAnnotation(null));
                books.addAll(libraryRepository.findByAnnotationContainingIgnoreCase(val));
            }
            return new ArrayList<>(books);
        }
        return Collections.emptyList();
    }
}
