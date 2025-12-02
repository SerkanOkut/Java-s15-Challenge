package library.model;

public class Book implements Borrowable {
    private final String id;
    private String title;
    private final Author author;
    private boolean isBorrowed = false;
    private Reader owner;

    public Book(String id, String title, Author author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    @Override
    public boolean isAvailable() {
        return !isBorrowed;
    }

    @Override
    public void borrow(Reader reader) {
        if (!isBorrowed) {
            isBorrowed = true;
            owner = reader;
        }
    }

    @Override
    public void returnBack() {
        isBorrowed = false;
        owner = null;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public Reader getOwner() {
        return owner;
    }
}
