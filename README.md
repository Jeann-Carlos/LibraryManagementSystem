
# BookManager

BookManager is a simple library management system built in Java with a Swing-based graphical user interface (GUI). The system allows users to add, search, borrow, and return books. It integrates with an SQLite database for persistent storage and includes input validation using regular expressions to ensure data integrity.

## Features

- **Add Books:** Users can add books to the library with a title, author, and ISBN. The system validates the input before adding the book.
- **Search Books:** Users can search for books by title.
- **Borrow and Return Books:** Users can borrow and return books using their ISBN.
- **Persistent Storage:** All book data is stored in an SQLite database for persistence across sessions.
- **Input Validation:** Title, author, and ISBN inputs are validated using regular expressions to ensure correct formatting.

## Technologies Used

- **Java:** Core programming language.
- **Swing:** For building the GUI.
- **SQLite:** For database storage.
- **JDBC:** For database connectivity.
- **Regex:** For input validation.

## Installation and Setup

1. **Clone the Repository:**
    ```sh
    git clone https://github.com/your-username/BookManager.git
    cd BookManager
    ```

2. **Ensure you have Java installed:** 
    Make sure you have JDK 11 or later installed on your machine. You can download it from [here](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).

3. **Run the Project:**
    You can run the project using Gradle:
    ```sh
    ./gradlew run
    ```

4. **Building the Project:**
    To build the project and generate a JAR file:
    ```sh
    ./gradlew build
    ```

5. **Running the JAR File:**
    After building the project, you can run it using the generated JAR file:
    ```sh
    java -jar build/libs/BookMan-1.0-SNAPSHOT.jar
    ```

### Note:
- **SQLite JDBC Driver:** The SQLite JDBC driver is included as a dependency in the Gradle build file. This driver allows your Java application to interact with SQLite databases directly, so there's no need to install SQLite separately.

- **Optional:** If you want to manually inspect or manage your SQLite database outside of this application, you might choose to install a standalone SQLite command-line tool or a GUI tool like [DB Browser for SQLite](https://sqlitebrowser.org/).

## ISBN Validation

The system supports both ISBN-10 and ISBN-13 formats. ISBNs are validated using regular expressions to ensure they meet the standard format before being added to the database.

- **ISBN-10 Structure:**
  - Length: 10 digits
  - Components: Group identifier, Publisher code, Title identifier, Check digit (0-9 or X)
  - Example: `0-306-40615-2`

- **ISBN-13 Structure:**
  - Length: 13 digits
  - Components: Prefix (978 or 979), Group identifier, Publisher code, Title identifier, Check digit (0-9)
  - Example: `978-3-16-148410-0`

### ISBN Validation Regex

```java
private boolean validateISBN(String isbn) {
    String isbnPattern = "^(?:\d{9}[\dX]|(?:978|979)[\ |-]?\d{1,5}[\ |-]?\d{1,7}[\ |-]?\d{1,6}[\ |-]?\d)$";
    return isbn.matches(isbnPattern);
}
```

This regex pattern ensures that the input matches either the ISBN-10 or ISBN-13 format, including optional separators like spaces or hyphens.

## GUI Overview

The GUI is built using Swing and consists of several panels for different operations:

- **Add Book Panel:** Allows the user to input the book title, author, and ISBN. Validation is applied to ensure correct input.
- **Search Book Panel:** Allows the user to search for a book by title.
- **Borrow/Return Book Panel:** Allows the user to borrow or return a book using its ISBN and a user ID.

### Example Usage

- **Adding a Book:**
  - Input the book's title, author, and ISBN in the respective fields.
  - Click "Add Book" to validate and add the book to the library.

- **Searching for a Book:**
  - Enter the title of the book in the search field.
  - Click "Search" to find the book and display its details.

- **Borrowing/Returning a Book:**
  - Input the ISBN and user ID in the respective fields.
  - Click "Borrow" to borrow the book, or "Return" to return it.

## Database Structure

The application uses SQLite for persistent storage. The `books` table is created with the following schema:

```sql
CREATE TABLE books (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    title TEXT NOT NULL,
    author TEXT NOT NULL,
    isbn TEXT NOT NULL UNIQUE,
    isBorrowed INTEGER NOT NULL DEFAULT 0
);
```

## Contributing

Contributions to the BookManager project are welcome! Feel free to fork the repository, make changes, and submit a pull request. If you encounter any issues, please open an issue on the GitHub repository.

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.

