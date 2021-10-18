package itmo.rbdplab2.service;

import itmo.rbdplab2.model.Book;
import itmo.rbdplab2.repository.LibraryRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibraryService {
    private final LibraryRepository libraryRepository;

    @Autowired
    public LibraryService(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
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
