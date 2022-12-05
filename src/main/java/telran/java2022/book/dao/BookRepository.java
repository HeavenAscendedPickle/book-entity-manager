package telran.java2022.book.dao;

import java.util.Optional;
import java.util.stream.Stream;


import telran.java2022.book.model.Book;

public interface BookRepository {
    
    Stream<Book> findByPublisherPublisherName(String name);
    
    Stream<Book> findByAuthorsName(String name);

    boolean existsById(String isbn);

    Optional<Book> findById(String isbn);

    Book save(Book book);

    void deleteById(String isbn);

}
