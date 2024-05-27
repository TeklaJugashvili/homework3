package lms;

import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LMS {

    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public boolean removeBook(Book book) {
        boolean removed = false;
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle().equals(book.getTitle()) && books.get(i).getAuthor().equals(book.getAuthor())) {
                books.remove(i);
                removed = true;
                break;
            }
        }
        return removed;
    }

    public boolean borrowBook(Book book, Student student) {
        boolean borrowed = false;
        if (!book.isBorrowed()) {
            book.setBorrowed(true);
            borrowed = true;
        }
        return borrowed;
    }

    public boolean returnBook(Book book) {
        boolean returned = false;
        if (book.isBorrowed()) {
            book.setBorrowed(false);
            returned = true;
        }
        return returned;
    }

    public void saveState(String filePath) {
        StringBuilder sb = new StringBuilder();
        for (Book book : books) {
            sb.append(book.getTitle()).append(",").append(book.getAuthor()).append("\n");
        }
        
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(sb.toString());
            System.out.println("Library state saved successfully to " + filePath);
        } catch (IOException e) {
            System.err.println("Failed to save library state: " + e.getMessage());
        }
    }

    public void loadState(String filePath) {
        books.clear(); 
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine())!= null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    String title = parts[0].trim();
                    String author = parts[1].trim();
                    books.add(new Book(title, author));
                }
            }
            System.out.println("Library state loaded successfully from " + filePath);
        } catch (IOException e) {
            System.err.println("Failed to load library state: " + e.getMessage());
        }
    }
}
