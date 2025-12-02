package library.model;

import java.util.*;

public class Library {
    private final List<Book> books = new ArrayList<>();
    private final Set<Reader> readers = new HashSet<>();
    private final Map<Book, Reader> borrowedBooks = new HashMap<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void registerReader(Reader reader) {
        readers.add(reader);
    }

    public List<Book> getBooks() {
        return books;
    }

    public Set<Reader> getReaders() {
        return readers;
    }

    public Map<Book, Reader> getBorrowedBooks() {
        return borrowedBooks;
    }

    public Optional<Book> findBookById(String id) {
        return books.stream()
                .filter(book -> book.getId().equalsIgnoreCase(id))
                .findFirst();
    }

    public List<Book> findBooksByTitle(String title) {
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .toList();
    }

    public List<Book> findBooksByAuthor(String authorName) {
        return books.stream()
                .filter(book -> book.getAuthor().getName().equalsIgnoreCase(authorName))
                .toList();
    }
}
