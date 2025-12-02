package library.model;

public interface Borrowable {
    boolean isAvailable();
    void borrow(Reader reader);
    void returnBack();
}
