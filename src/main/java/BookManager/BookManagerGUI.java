package BookManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BookManagerGUI extends JFrame {
    private Library library;
    private JTextArea bookListArea;
    private JTextField titleField, authorField, isbnField, searchField, borrowIsbnField, userIdField;

    public BookManagerGUI() {
        library = new Library();
        setTitle("Library Management System");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main Panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Book Entry Section
        JLabel bookEntryLabel = new JLabel("Add a Book:");
        titleField = new JTextField(20);
        titleField.setBorder(BorderFactory.createTitledBorder("Title"));
        authorField = new JTextField(20);
        authorField.setBorder(BorderFactory.createTitledBorder("Author"));
        isbnField = new JTextField(20);
        isbnField.setBorder(BorderFactory.createTitledBorder("ISBN"));
        JButton addBookButton = new JButton("Add Book");

        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });

        panel.add(bookEntryLabel);
        panel.add(titleField);
        panel.add(authorField);
        panel.add(isbnField);
        panel.add(addBookButton);

        // Search Section
        JLabel searchLabel = new JLabel("Search for a Book:");
        searchField = new JTextField(20);
        searchField.setBorder(BorderFactory.createTitledBorder("Book Tittle /  Author"));
        JButton searchButton = new JButton("Search");
        JLabel searchResultLabel = new JLabel();

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchBook(searchResultLabel);
            }
        });

        panel.add(searchLabel);
        panel.add(searchField);
        panel.add(searchButton);
        panel.add(searchResultLabel);

        // Book List Display
        JLabel bookListLabel = new JLabel("Books in Library:");
        bookListArea = new JTextArea(10, 40);
        bookListArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(bookListArea);

        panel.add(bookListLabel);
        panel.add(scrollPane);

        // Borrow/Return Section
        JLabel borrowLabel = new JLabel("Borrow/Return a Book:");
        borrowIsbnField = new JTextField(20);
        borrowIsbnField.setBorder(BorderFactory.createTitledBorder("Book ISBN"));
        userIdField = new JTextField(20);
        userIdField.setBorder(BorderFactory.createTitledBorder("User ID"));
        JButton borrowButton = new JButton("Borrow");
        JButton returnButton = new JButton("Return");

        borrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borrowBook();
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnBook();
            }
        });

        panel.add(borrowLabel);
        panel.add(borrowIsbnField);
        panel.add(userIdField);
        panel.add(borrowButton);
        panel.add(returnButton);

        add(panel);
        updateBookList();
    }

    private void addBook() {
        String title = titleField.getText();
        String author = authorField.getText();
        String isbn = isbnField.getText();

        if (validateTitle(title) && validateAuthor(author) && validateISBN(isbn)) {
            library.addBook(new Book(title, author, isbn, false));
            titleField.setText("");
            authorField.setText("");
            isbnField.setText("");
            updateBookList();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid input. Please check the title, author, or ISBN format.");
        }
    }

    private boolean validateTitle(String title) {
        String titlePattern = "^[a-zA-Z0-9\\s,.'-]{1,100}$"; // Letters, numbers, spaces, punctuation
        return title.matches(titlePattern);
    }

    private boolean validateAuthor(String author) {
        String authorPattern = "^[a-zA-Z\\s,.'-]{1,100}$"; // Letters, spaces, punctuation
        return author.matches(authorPattern);
    }

    private boolean validateISBN(String isbn) {
        String isbnPattern = "^(?:\\d{9}[\\dX]|(?:978|979)[\\ |-]?\\d{1,5}[\\ |-]?\\d{1,7}[\\ |-]?\\d{1,6}[\\ |-]?\\d)$";
        return isbn.matches(isbnPattern);
    }



    private void searchBook(JLabel searchResultLabel) {
        String bookstring = searchField.getText();
        Book book = library.searchBook(bookstring);
        if (book != null) {
            searchResultLabel.setText("Found: " + book.toString());
        } else {
            searchResultLabel.setText("Book not found.");
        }
    }



    private void borrowBook() {
        String isbn = borrowIsbnField.getText();
        String userId = userIdField.getText();
        if (!isbn.isEmpty() && !userId.isEmpty()) {
            library.borrowBook(isbn, userId);
            updateBookList();
        } else {
            JOptionPane.showMessageDialog(this, "Both ISBN and User ID must be provided to borrow a book.");
        }
    }

    private void returnBook() {
        String isbn = borrowIsbnField.getText();
        String userId = userIdField.getText();
        if (!isbn.isEmpty() && !userId.isEmpty()) {
            library.returnBook(isbn, userId);
            updateBookList();
        } else {
            JOptionPane.showMessageDialog(this, "Both ISBN and User ID must be provided to return a book.");
        }
    }

    private void updateBookList() {
        StringBuilder books = new StringBuilder();
        for (Book book : library.getBooks()) {
            books.append(book.toString()).append("\n");
        }
        bookListArea.setText(books.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                BookManagerGUI frame = new BookManagerGUI();
                frame.setVisible(true);
            }
        });
    }
}
