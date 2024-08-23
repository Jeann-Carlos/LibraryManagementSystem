package BookManager;

import java.util.List;

public class Library {
    private DatabaseManager dbManager;

    public Library() {
        this.dbManager = new DatabaseManager();
    }

    // Add a new book to the library (and database)
    public void addBook(Book book) {
        dbManager.addBook(book);
    }

    // Get a list of all books in the library (from database)
    public List<Book> getBooks() {
        return dbManager.getAllBooks();
    }

    // Search for a book by title in the library (from database)
    public Book searchBook(String string) {
        for (Book book : getBooks()) {
            if (book.getTitle().equalsIgnoreCase(string) || book.getAuthor().equalsIgnoreCase(string)) {
                return book;
            }
        }
        return null;
    }



    // Borrow a book by ISBN
    public void borrowBook(String isbn, String userId) {
        Book book = dbManager.getBookByIsbn(isbn);
        if (book != null && !book.isBorrowed()) {
            book.borrowBook();
            dbManager.updateBookStatus(isbn, true);
            System.out.println("Book borrowed by user: " + userId);
        } else {
            System.out.println("Book is not available.");
        }
    }

    // Return a book by ISBN
    public void returnBook(String isbn, String userId) {
        Book book = dbManager.getBookByIsbn(isbn);
        if (book != null && book.isBorrowed()) {
            book.returnBook();
            dbManager.updateBookStatus(isbn, false);
            System.out.println("Book returned by user: " + userId);
        } else {
            System.out.println("This book was not borrowed.");
        }
    }
}
