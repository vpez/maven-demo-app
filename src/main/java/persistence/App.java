package persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import persistence.entity.Book;
import persistence.service.BookService;

import java.util.List;

public class App {

    private static final List<String> names = List.of("Core Java Volume", "Effective Java",
            "Head First Java", "Java Concurrency in Practice");

    private BookService bookService;

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager entityManager = factory.createEntityManager();

        App app = new App();
        app.setBookService(new BookService(entityManager));

        app.startApp();
    }

    public void startApp() {
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

