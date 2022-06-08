package persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import persistence.entity.Author;
import persistence.entity.Book;
import persistence.entity.Genre;
import persistence.service.BookService;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class App {

    private static final List<String> names = List.of("Core Java Volume", "Effective Java",
            "Head First Java", "Java Concurrency in Practice");

    private BookService bookService;

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager entityManager = factory.createEntityManager();

        App app = new App();
        app.setBookService(new BookService(entityManager));

        app.testBookCRUD();

        // Just pass the EntityManager since too lazy to create a service for authors
        app.testRelationships(entityManager);
    }

    public void testRelationships(EntityManager entityManager) {
        Author author = new Author();
        author.setName("Jack London");

        Book book = new Book();
        book.setName("White Fang");
        book.setAuthor(author);

        entityManager.getTransaction().begin();
        entityManager.persist(author);
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

    public void testBookCRUD() {
        Book[] books = new Book[names.size()];
        for (int i = 0; i < names.size(); i++) {
            books[i] = new Book();
            books[i].setName(names.get(i));
        }

        bookService.persist(books);

        // Get books and verify
        Book book = bookService.find(2L);
        if (book == null || !"Effective Java".equals(book.getName())) {
            System.err.println("Wrong values");
        }

        // Test remove
        bookService.remove(2L);
        book = bookService.find(2L);
        if (book != null) {
            System.err.println("Book should be deleted");
        }

        // Test update
        bookService.addCost(1L, 100.0);
        book = bookService.find(1L);
        if (book.getCost() != 100.0) {
            System.err.println("Book cost not correct");
        }
    }

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }
}

