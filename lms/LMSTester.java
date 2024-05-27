package lms;

public class LMSTester {
    public static void main(String[] args) {
        LMS iliauniLibrary = new LMS();

        Book lor = new Book("Lord of the Rings", "J.R.R. Tolkien");
        Book oop = new Book("OOP Principles", "Paata Gogishvili");
        iliauniLibrary.addBook(lor);
        iliauniLibrary.addBook(oop);

        Student gocha = new Student("Gocha", "Gegeshidze", "123456789");
        iliauniLibrary.borrowBook(lor, gocha);

        Student maka = new Student("Maka", "Lobjanidze", "987654321");
        iliauniLibrary.borrowBook(oop, maka);

        iliauniLibrary.saveState("state.csv");

        iliauniLibrary.loadState("state.csv");
    }
}

