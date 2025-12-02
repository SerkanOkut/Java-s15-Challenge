import library.manager.LibraryManager;
import library.model.*;
import library.service.BookService;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        LibraryManager manager = new LibraryManager(library);
        BookService bookService = new BookService(library);
        Map<String, Bill> billMap = new HashMap<>();

        System.out.println("📚 KÜTÜPHANE SİSTEMİNE HOŞGELDİNİZ 📚");

        while (true) {
            System.out.println("\n== Menü ==");
            System.out.println("1. Kitap Ekle");
            System.out.println("2. Kitap Ara (ID, Başlık, Yazar)");
            System.out.println("3. Kitap Güncelle");
            System.out.println("4. Kitap Sil");
            System.out.println("5. Kategoriye Göre Listele");
            System.out.println("6. Yazara Göre Listele");
            System.out.println("7. Kitap Ödünç Al");
            System.out.println("8. Kitap İade Et");
            System.out.println("9. Tüm Ödünç Kitaplar");
            System.out.println("0. Çıkış");
            System.out.print("Seçim: ");

            int secim = Integer.parseInt(scanner.nextLine());

            switch (secim) {
                case 1 -> {
                    System.out.print("Kitap ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Kitap Adı: ");
                    String title = scanner.nextLine();
                    System.out.print("Yazar Adı: ");
                    String authorName = scanner.nextLine();
                    Author author = new Author(authorName, UUID.randomUUID().toString());
                    Book book = new Book(id, title, author);
                    library.addBook(book);
                    System.out.println("✅ Kitap eklendi.");
                }
                case 2 -> {
                    System.out.println("1. ID ile\n2. Başlık ile\n3. Yazar ile");
                    String sec = scanner.nextLine();
                    switch (sec) {
                        case "1" -> {
                            System.out.print("Kitap ID: ");
                            String id = scanner.nextLine();
                            Book b = library.findBookById(id).orElse(null);
                            System.out.println(b != null ? b.getTitle() : "❌ Bulunamadı.");
                        }
                        case "2" -> {
                            System.out.print("Başlık: ");
                            String title = scanner.nextLine();
                            var books = library.findBooksByTitle(title);
                            books.forEach(b -> System.out.println(b.getTitle()));
                        }
                        case "3" -> {
                            System.out.print("Yazar Adı: ");
                            String name = scanner.nextLine();
                            var books = library.findBooksByAuthor(name);
                            books.forEach(b -> System.out.println(b.getTitle()));
                        }
                    }
                }
                case 3 -> {
                    System.out.print("Güncellenecek Kitap ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Yeni Başlık: ");
                    String newTitle = scanner.nextLine();
                    boolean result = bookService.updateBookTitle(id, newTitle);
                    System.out.println(result ? "✅ Güncellendi." : "❌ Bulunamadı.");
                }
                case 4 -> {
                    System.out.print("Silinecek Kitap ID: ");
                    String id = scanner.nextLine();
                    boolean result = bookService.deleteBook(id);
                    System.out.println(result ? "🗑️ Kitap silindi." : "❌ Bulunamadı.");
                }
                case 5 -> {
                    System.out.print("Kategori Adı: ");
                    String cat = scanner.nextLine();
                    var books = bookService.listBooksByCategory(cat);
                    books.forEach(b -> System.out.println(b.getTitle()));
                }
                case 6 -> {
                    System.out.print("Yazar Adı: ");
                    String author = scanner.nextLine();
                    var books = bookService.listBooksByAuthor(author);
                    books.forEach(b -> System.out.println(b.getTitle()));
                }
                case 7 -> {
                    System.out.print("Okuyucu Adı: ");
                    String name = scanner.nextLine();
                    System.out.print("Okuyucu ID: ");
                    String userId = scanner.nextLine();
                    Reader reader = new Reader(name, userId);
                    library.registerReader(reader);

                    System.out.print("Kitap ID: ");
                    String bookId = scanner.nextLine();

                    if (reader.getBorrowedBooks().size() >= 5) {
                        System.out.println("❌ Kitap limiti doldu.");
                        break;
                    }

                    boolean ok = manager.borrowBook(bookId, reader);
                    if (ok) {
                        Book book = library.findBookById(bookId).get();
                        Bill bill = new Bill(UUID.randomUUID().toString(), reader, book, 10.0);
                        billMap.put(bookId, bill);
                        bill.print();
                    } else {
                        System.out.println("❌ Kitap alınamadı.");
                    }
                }
                case 8 -> {
                    System.out.print("Okuyucu ID: ");
                    String userId = scanner.nextLine();
                    System.out.print("Kitap ID: ");
                    String bookId = scanner.nextLine();

                    Reader reader = library.getReaders().stream()
                            .filter(r -> r.getId().equalsIgnoreCase(userId))
                            .findFirst().orElse(null);

                    if (reader != null && manager.returnBook(bookId, reader)) {
                        System.out.println("✅ İade başarılı.");
                        if (billMap.containsKey(bookId)) {
                            Bill bill = billMap.get(bookId);
                            bill.refund();
                            bill.print();
                        }
                    } else {
                        System.out.println("❌ İade başarısız.");
                    }
                }
                case 9 -> manager.printBorrowedBooks();
                case 0 -> {
                    System.out.println("👋 Görüşürüz!");
                    return;
                }
                default -> System.out.println("❗ Geçersiz seçim.");
            }
        }
    }
}
