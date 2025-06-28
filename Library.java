import java.io.*;
import java.util.*;

public class Library {
    private List<Book> books;
    private final String bookFile = "data/books.txt";

    public Library() {
        books = new ArrayList<>();
        loadBooks();
    }

    private void loadBooks() {
        try (BufferedReader br = new BufferedReader(new FileReader(bookFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                books.add(Book.fromString(line));
            }
        } catch (IOException e) {
            System.out.println("Error loading books.");
        }
    }

    private void saveBooks() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(bookFile))) {
            for (Book book : books) {
                bw.write(book.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving books.");
        }
    }

    public void displayBooks() {
        for (Book book : books) {
            System.out.printf("ID: %s | Title: %s | Author: %s | Available: %s\n",
                    book.getId(), book.getTitle(), book.getAuthor(), book.isAvailable());
        }
    }

    public void addBook(Book book) {
        books.add(book);
        saveBooks();
        System.out.println("Book added.");
    }

    public void removeBook(String id) {
        books.removeIf(book -> book.getId().equals(id));
        saveBooks();
        System.out.println("Book removed.");
    }

    public void borrowBook(String id) {
        for (Book book : books) {
            if (book.getId().equals(id) && book.isAvailable()) {
                book.setAvailable(false);
                saveBooks();
                System.out.println("Book borrowed.");
                return;
            }
        }
        System.out.println("Book not available.");
    }

    public void returnBook(String id) {
        for (Book book : books) {
            if (book.getId().equals(id) && !book.isAvailable()) {
                book.setAvailable(true);
                saveBooks();
                System.out.println("Book returned.");
                return;
            }
        }
        System.out.println("Invalid return.");
    }
}
