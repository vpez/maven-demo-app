package persistence;

import jakarta.persistence.EntityManager;
import persistence.entity.Book;
import persistence.service.BookService;

import java.util.List;

class BasicCRUD implements App.TestCase {

    private static final List<String> names = List.of("Core Java Volume", "Effective Java",
            "Head First Java", "Java Concurrency in Practice");

    @Override
    public void run(EntityManager entityManager) {
        BookService bookService = new BookService(entityManager);

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
}
