package itmo.rbdplab2.controller;

import itmo.rbdplab2.model.Book;
import itmo.rbdplab2.service.LibraryService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library")
public class LibraryController {

    private final LibraryService libraryService;

    @Autowired
    LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }
    //добавить книгу
    //поиск
    //по названию или фрагменту
    //по имени автора
    //ключевое словл
    //выход

    @PostMapping("/add")
    public Book addFood (
        @RequestParam(name = "name") String name,
        @RequestParam(name = "author") String author,
        @RequestParam(name = "genre") String genre,
        @RequestParam(name = "annotation") String annotation,
        @RequestParam(name = "date") String date,
        @RequestParam(name = "isbn") String isbn
    ) {
        return libraryService.save(new Book(name, author, genre,
            LocalDate.parse(date), annotation, isbn));
    }

    @GetMapping("/find")
    public ResponseEntity<Iterable<Book>> addFood (
        @RequestParam(name = "byName") Boolean nameFlag,
        @RequestParam(name = "byAuthor") Boolean authorFlag,
        @RequestParam(name = "byKeyWord") Boolean keyWordsFlag,
        @RequestParam(name = "value") String value
    ) {
        List<Book> list = libraryService.find(nameFlag, authorFlag, keyWordsFlag, value);
        return new ResponseEntity<Iterable<Book>>(list, HttpStatus.OK);
    }
}
