import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Single Responsibility Principle (SRP)
// Book class managing book details
class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}

// Member class managing member details
class Member {
    private String name;
    private String memberId;

    public Member(String name, String memberId) {
        this.name = name;
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public String getMemberId() {
        return memberId;
    }
}

// Interface for borrowing actions (Open/Closed Principle)
interface Borrowable {
    void borrowBook(Book book);
    void returnBook(Book book);
}

// BorrowManager class for handling borrowing and returning books
class BorrowManager implements Borrowable {
    private Map<Member, List<Book>> borrowedBooks;

    public BorrowManager() {
        borrowedBooks = new HashMap<>();
    }

    @Override
    public void borrowBook(Book book) {
        // Logic to borrow a book
    }

    @Override
    public void returnBook(Book book) {
        // Logic to return a book
    }

    public void borrowBook(Member member, Book book) {
        if (book.isAvailable()) {
            book.setAvailable(false);
            borrowedBooks.computeIfAbsent(member, k -> new ArrayList<>()).add(book);
            System.out.println(member.getName() + " borrowed " + book.getTitle());
        } else {
            System.out.println(book.getTitle() + " is currently not available.");
        }
    }

    public void returnBook(Member member, Book book) {
        List<Book> books = borrowedBooks.get(member);
        if (books != null && books.remove(book)) {
            book.setAvailable(true);
            System.out.println(member.getName() + " returned " + book.getTitle());
        } else {
            System.out.println(member.getName() + " did not borrow " + book.getTitle());
        }
    }
}

// Library class to manage books and members
class Library {
    private List<Book> books;
    private List<Member> members;
    private BorrowManager borrowManager;

    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
        borrowManager = new BorrowManager();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public List<Book> getAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.isAvailable()) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }

    public void borrowBook(Member member, Book book) {
        borrowManager.borrowBook(member, book);
    }

    public void returnBook(Member member, Book book) {
        borrowManager.returnBook(member, book);
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public List<Member> getAllMembers() {
        return members;
    }
}

// Client class to demonstrate the Library Management System
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();

        // Adding books to the library
        Book book1 = new Book("1984", "George Orwell", "1234567890");
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", "0987654321");
        library.addBook(book1);
        library.addBook(book2);

        // Adding members to the library
        Member member1 = new Member("Alice", "M001");
        Member member2 = new Member("Bob", "M002");
        library.addMember(member1);
        library.addMember(member2);

        // Demonstrating borrowing and returning books
        library.borrowBook(member1, book1); // Alice borrows 1984
        library.borrowBook(member2, book1); // Bob tries to borrow 1984 (not available)
        library.returnBook(member1, book1);  // Alice returns 1984
        library.borrowBook(member2, book1); // Bob borrows 1984 now
    }
}
