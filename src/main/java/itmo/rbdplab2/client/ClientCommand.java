package itmo.rbdplab2.client;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class ClientCommand {
    public void addBook(String name, String author, String genre,
        String annotation, String date, String isbn) throws IOException {
        String BASE_URL = "http://localhost:8080/library/add";
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(BASE_URL)
            .queryParam("name", name)
            .queryParam("author", author)
            .queryParam("genre", genre)
            .queryParam("annotation", annotation)
            .queryParam("date", date)
            .queryParam("isbn", isbn);
        try {
            ResponseEntity<Book> responseEntity = restTemplate.exchange(builder.toUriString(),
                HttpMethod.POST, null,
                new ParameterizedTypeReference<Book>(){});
        } catch (Exception e) {
            System.out.println("something goes wrong");
        }
    }

    public void findBook(String byName, String byAuthor, String byKeyWord, String value) {
        String BASE_URL = "http://localhost:8080/library/find";
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(BASE_URL)
            .queryParam("byName", byName)
            .queryParam("byAuthor", byAuthor)
            .queryParam("byKeyWord", byKeyWord)
            .queryParam("value", value);
        try {
            ResponseEntity<List<Book>> responseEntity = restTemplate.exchange(builder.toUriString(),
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Book>>(){});
            System.out.println("Book List");
            for(Book book : Objects.requireNonNull(responseEntity.getBody())) {
                System.out.println("Name: " + book.getName());
                System.out.println("Author: " + book.getAuthorName());
                System.out.println("Genre: " + book.getGenres());
                if (book.getAnnotation() != null) {
                    System.out.println("Annotation: " + book.getAnnotation());
                }
                System.out.println("Date: " + book.getDate());
                System.out.println("ISBN: " + book.getIsbn());
                System.out.println("===============================");
            }
        } catch (Exception e) {
            System.out.println("something goes wrong");
        }
    }
}
