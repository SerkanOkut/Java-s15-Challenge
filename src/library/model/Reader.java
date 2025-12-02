package library.model;

import java.util.ArrayList;
import java.util.List;

public class Reader extends Person {
    private final List<Book> borrowedBooks = new ArrayList<>();
    private static final int BOOK_LIMIT = 5;

    public Reader(String name, String id) {
        super(name, id);
    }

    public boolean borrowBook(Book book) {
        if (borrowedBooks.size() < BOOK_LIMIT) {
            borrowedBooks.add(book);
            return true;
        }
        return false;
    }

    public boolean returnBook(Book book) {
        return borrowedBooks.remove(book);
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    @Override
    public void showWhoYouAre() {
        System.out.println("Ben bir okuyucuyum: " + name);
    }
}
