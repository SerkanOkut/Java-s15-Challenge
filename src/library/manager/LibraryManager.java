package library.manager;

import library.model.*;

public class LibraryManager {
    private final Library library;

    public LibraryManager(Library library) {
        this.library = library;
    }

    public boolean borrowBook(String bookId, Reader reader) {
        Book book = library.findBookById(bookId).orElse(null);
        if (book != null && book.isAvailable() && reader.borrowBook(book)) {
            book.borrow(reader);
            library.getBorrowedBooks().put(book, reader);
            return true;
        }
        return false;
    }

    public boolean returnBook(String bookId, Reader reader) {
        Book book = library.findBookById(bookId).orElse(null);
        if (book != null && book.getOwner() == reader) {
            book.returnBack();
            reader.returnBook(book);
            library.getBorrowedBooks().remove(book);
            return true;
        }
        return false;
    }

    public void printBorrowedBooks() {
        for (var entry : library.getBorrowedBooks().entrySet()) {
            System.out.println(entry.getKey().getTitle() + " → " + entry.getValue().getName());
        }
    }
}
