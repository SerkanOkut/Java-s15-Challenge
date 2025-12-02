package library.model;

public class Librarian extends Person {
    public Librarian(String name, String id) {
        super(name, id);
    }

    @Override
    public void showWhoYouAre() {
        System.out.println("Ben bir kütüphane görevlisiyim: " + name);
    }
}
