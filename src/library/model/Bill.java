package library.model;

public class Bill {
    private final String billId;
    private final Reader reader;
    private final Book book;
    private final double amount;
    private boolean isRefunded;

    public Bill(String billId, Reader reader, Book book, double amount) {
        this.billId = billId;
        this.reader = reader;
        this.book = book;
        this.amount = amount;
        this.isRefunded = false;
    }

    public void refund() {
        isRefunded = true;
    }

    public boolean isRefunded() {
        return isRefunded;
    }

    public void print() {
        System.out.println("----- Fatura -----");
        System.out.println("Fatura No: " + billId);
        System.out.println("Okuyucu: " + reader.getName());
        System.out.println("Kitap: " + book.getTitle());
        System.out.println("Tutar: " + amount + "₺");
        System.out.println(isRefunded ? "(İADE EDİLDİ)" : "");
    }
}
