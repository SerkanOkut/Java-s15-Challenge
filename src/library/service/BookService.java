package library.service;

import library.model.Book;
import library.model.Library;

import java.util.List;

public class BookService {
    private final Library library;

    public BookService(Library library) {
        this.library = library;
    }

    public boolean updateBookTitle(String bookId, String newTitle) {
        return library.findBookById(bookId)
                .map(book -> {
                    book.setTitle(newTitle);
                    return true;
                })
                .orElse(false);
    }

    public boolean deleteBook(String bookId) {
        return library.findBookById(bookId)
                .map(book -> library.getBooks().remove(book))
                .orElse(false);
    }

    public List<Book> listBooksByCategory(String categoryName) {
        return library.getBooks().stream()
                .filter(book -> book.getClass().getSimpleName().equalsIgnoreCase(categoryName))
                .toList();
    }

    public List<Book> listBooksByAuthor(String authorName) {
        return library.findBooksByAuthor(authorName);
    }
}
