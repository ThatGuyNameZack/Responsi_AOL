import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// Book class to represent a book
class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true;  // Available by default
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public String toString() {
        return "Book{" + "Title='" + title + '\'' + ", Author='" + author + '\'' + ", ISBN='" + isbn + '\'' + ", Available=" + isAvailable + '}';
    }
}

// Member class to represent a library member
class Member {
    private String memberId;
    private String name;
    private String contactInfo;

    public Member(String memberId, String name, String contactInfo) {
        this.memberId = memberId;
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public String getMemberId() {
        return memberId;
    }

    @Override
    public String toString() {
        return "Member{" + "MemberID='" + memberId + '\'' + ", Name='" + name + '\'' + ", ContactInfo='" + contactInfo + '\'' + '}';
    }
}

// Librarian class with management capabilities
class Librarian {
    private String librarianId;

    public Librarian(String librarianId) {
        this.librarianId = librarianId;
    }

    public void addBook(Map<String, Book> books, Book book) {
        books.put(book.getIsbn(), book);
        System.out.println("Book added: " + book);
    }

    public void removeBook(Map<String, Book> books, String isbn) {
        Book removedBook = books.remove(isbn);
        if (removedBook != null) {
            System.out.println("Book removed: " + removedBook);
        } else {
            System.out.println("Book with ISBN " + isbn + " not found.");
        }
    }

    public void addMember(Map<String, Member> members, Member member) {
        members.put(member.getMemberId(), member);
        System.out.println("Member added: " + member);
    }

    public void removeMember(Map<String, Member> members, String memberId) {
        Member removedMember = members.remove(memberId);
        if (removedMember != null) {
            System.out.println("Member removed: " + removedMember);
        } else {
            System.out.println("Member with ID " + memberId + " not found.");
        }
    }
}

// Transaction class to record borrowing and returning books
class Transaction {
    private static int idCounter = 1;
    private int transactionId;
    private String memberId;
    private String bookIsbn;
    private Date transactionDate;
    private String transactionType;

    public Transaction(String memberId, String bookIsbn, String transactionType) {
        this.transactionId = idCounter++;
        this.memberId = memberId;
        this.bookIsbn = bookIsbn;
        this.transactionDate = new Date();
        this.transactionType = transactionType;
    }

    @Override
    public String toString() {
        return "Transaction{" + "TransactionID=" + transactionId + ", MemberID='" + memberId + '\'' + ", BookISBN='" + bookIsbn + '\'' + ", Date=" + transactionDate + ", Type='" + transactionType + '\'' + '}';
    }
}

// Library System to manage the library
public class LibrarySystem {
    private Map<String, Book> books = new HashMap<>();
    private Map<String, Member> members = new HashMap<>();
    private ArrayList<Transaction> transactions = new ArrayList<>();

    private Librarian librarian;

    public LibrarySystem(Librarian librarian) {
        this.librarian = librarian;
    }

    // Borrow a book
    public void borrowBook(String memberId, String isbn) {
        Book book = books.get(isbn);
        Member member = members.get(memberId);

        if (book != null && member != null && book.isAvailable()) {
            book.setAvailable(false);
            transactions.add(new Transaction(memberId, isbn, "Borrow"));
            System.out.println("Book borrowed: " + book);
        } else {
            System.out.println("Borrowing failed: Book is unavailable or Member/Book not found.");
        }
    }

    // Return a book
    public void returnBook(String memberId, String isbn) {
        Book book = books.get(isbn);
        Member member = members.get(memberId);

        if (book != null && member != null && !book.isAvailable()) {
            book.setAvailable(true);
            transactions.add(new Transaction(memberId, isbn, "Return"));
            System.out.println("Book returned: " + book);
        } else {
            System.out.println("Returning failed: Book is already available or Member/Book not found.");
        }
    }

    // Display all transactions
    public void displayTransactions() {
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    public static void main(String[] args) {
        // Initialize Librarian
        Librarian librarian = new Librarian("L1");

        // Initialize Library System
        LibrarySystem library = new LibrarySystem(librarian);

        // Add some books and members
        librarian.addBook(library.books, new Book("Java Programming", "Author A", "ISBN001"));
        librarian.addBook(library.books, new Book("Python Programming", "Author B", "ISBN002"));

        librarian.addMember(library.members, new Member("M1", "John Doe", "123456789"));
        librarian.addMember(library.members, new Member("M2", "Jane Doe", "987654321"));

        // Borrow and Return Books
        library.borrowBook("M1", "ISBN001");
        library.returnBook("M1", "ISBN001");

        // Display Transactions
        library.displayTransactions();
    }
}