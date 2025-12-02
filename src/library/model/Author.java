package library.model;

import java.util.Set;
import java.util.HashSet;
import java.util.List;

public class Author extends Person {
    private final Set<Book> books = new HashSet<>();

    public Author(String name, String id) {
        super(name, id);
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public Set<Book> getBooks() {
        return books;
    }

    @Override
    public void showWhoYouAre() {
        System.out.println("Ben bir yazarım: " + name);
    }
}
