package persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.junit.Before;
import org.junit.Test;
import persistence.entity.Book;

import static org.junit.Assert.*;

public class PersistenceTest {

    private EntityManager entityManager;

    @Before
    public void before() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        entityManager = factory.createEntityManager();
    }

    @Test
    public void testCRUD() {
        new BasicCRUD().run(entityManager);

        Book book = entityManager.find(Book.class, 1L);
        assertEquals("Core Java Volume", book.getName());
        assertEquals(100.0, book.getCost(), 0.01);

        book = entityManager.find(Book.class, 2L);
        assertNull(book);
    }

    @Test
    public void testBookGeneres() {
        new BasicRelationships().run(entityManager);

        TypedQuery<Book> query = entityManager.createQuery("select b from Book b where b.name = 'White Fang'", Book.class);
        Book book = query.getResultList().get(0);
        assertEquals("Jack London", book.getAuthor().getName());
        assertEquals(2, book.getGenres().size());
    }
}
