package persistence.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import persistence.entity.Book;

public class BookService {

    private final EntityManager entityManager;
    private final EntityTransaction transaction;

    public BookService(EntityManager entityManager) {
        this.entityManager = entityManager;
        transaction = entityManager.getTransaction();
    }

    public void persist(Book book) {
        transaction.begin();
        entityManager.persist(book);
        transaction.commit();
    }

    public void persist(Book[] books) {
        transaction.begin();
        for (Book book : books) {
            entityManager.persist(book);
        }
        transaction.commit();
    }

    public Book find(Long id) {
        return entityManager.find(Book.class, id);
    }

    public void remove(Long id) {
        Book book = entityManager.find(Book.class, id);
        if (book == null) {
            return;
        }
        transaction.begin();
        entityManager.remove(book);
        transaction.commit();
    }

    public void addCost(Long id, double addition) {
        Book book = entityManager.find(Book.class, id);
        transaction.begin();
        book.setCost(book.getCost() + addition);
        transaction.commit();
    }
}
