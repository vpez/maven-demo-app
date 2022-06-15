package persistence;

import jakarta.persistence.EntityManager;
import persistence.entity.Author;
import persistence.entity.Book;
import persistence.entity.Genre;

import java.util.HashSet;
import java.util.stream.Collectors;

class BasicRelationships implements App.TestCase {
    @Override
    public void run(EntityManager entityManager) {
        Author author = new Author();
        author.setName("Jack London");

        Book book = new Book();
        book.setName("White Fang");
        book.setAuthor(author);

        entityManager.getTransaction().begin();
        // persisting author is automatically done by JPA since it is marked as cascade
        entityManager.persist(book);
        entityManager.getTransaction().commit();

        // Use the ID provided by the DB
        Long returnedId = book.getId();
        Book book1 = entityManager.find(Book.class, returnedId);
        System.out.println(book1.getAuthor().getName());

        // Test the many-to-many relationship
        // --- Begin transaction
        entityManager.getTransaction().begin();

        // Prepare some genres
        Genre genre1 = new Genre();
        genre1.setTitle("Novel");
        entityManager.persist(genre1);

        Genre genre2 = new Genre();
        genre2.setTitle("Adventure fiction");
        entityManager.persist(genre2);

        book1 = entityManager.merge(book1);
        book1.setGenres(new HashSet<>());
        book1.getGenres().add(genre1);
        book1.getGenres().add(genre2);
        entityManager.persist(book1);
        entityManager.getTransaction().commit();
        // --- End of transaction

        Book book2 = entityManager.find(Book.class, returnedId);
        System.out.println("Generes: " + book2.getGenres().stream()
                .map(Genre::getTitle)
                .sorted()
                .collect(Collectors.joining(", ")));
    }
}
