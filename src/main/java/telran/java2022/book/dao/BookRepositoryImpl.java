package telran.java2022.book.dao;

import java.util.Optional;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import telran.java2022.book.model.Book;

@Repository
public class BookRepositoryImpl implements BookRepository {
    @PersistenceContext
    EntityManager em;
    
    @Override
    public Stream<Book> findByPublisherPublisherName(String name) {
	TypedQuery<Book> query = em.createQuery(
		"select books from Publisher p where p.publisherName=?1",
		Book.class);
	query.setParameter(1, name);
	return query.getResultStream();
    }

    @Override
    public Stream<Book> findByAuthorsName(String name) {
	TypedQuery<Book> query = em.createQuery(
		"select books from Author a where a.name=?1",
		Book.class);
	query.setParameter(1, name);
	return query.getResultStream();
    }

    @Override
    public boolean existsById(String isbn) {
	return em.find(Book.class, isbn) != null;
    }

    @Override
    public Optional<Book> findById(String isbn) {
	return Optional.ofNullable(em.find(Book.class, isbn));
    }

    @Override
    // @Transactional
    public Book save(Book book) {
	em.persist(book);
	// em.merge(book);
	return book;

    }

    @Override
    @Transactional
    public void deleteById(String isbn) {
	Book book = em.find(Book.class, isbn);
	em.remove(book);

    }

}
