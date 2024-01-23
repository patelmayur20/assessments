package com.assessments;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class LibraryManagementSystem {

    private static final String PASSWORD = "m123"; 
    private static String currentPassword = PASSWORD;
    private static ArrayList<Book> books = new ArrayList<>();
    private static HashSet<String> bookTitles = new HashSet<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter password: ");
        String enteredPassword = scanner.nextLine();

        if (enteredPassword.equals(currentPassword)) {
            System.out.println("Password correct. Access granted.");
            displayMenu(scanner);
        } else {
            System.out.println("Incorrect password. Exiting program.");
        }
    }

    private static void displayMenu(Scanner scanner) {
        int choice;

        do {
            System.out.println("\n--- Library Management System Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. Delete Book");
            System.out.println("3. Search Book");
            System.out.println("4. View All Books");
            System.out.println("5. Change Password");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addBook(scanner);
                    break;
                case 2:
                    deleteBook(scanner);
                    break;
                case 3:
                    searchBook(scanner);
                    break;
                case 4:
                    viewAllBooks();
                    break;
                case 5:
                    changePassword(scanner);
                    break;
                case 6:
                    exitProgram();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 6);
    }

    private static void addBook(Scanner scanner) {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();

        if (bookTitles.contains(title)) {
            System.out.println("Book with the same title already exists. Please choose a different title.");
            return;
        }

        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        System.out.print("Enter book category: ");
        String category = scanner.nextLine();

        Book newBook = new Book(title, author, category);
        books.add(newBook);
        bookTitles.add(title);
        System.out.println("Book added successfully.");
    }

    private static void deleteBook(Scanner scanner) {
        System.out.print("Enter book title to delete: ");
        String title = scanner.nextLine();

        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                books.remove(book);
                bookTitles.remove(title);
                System.out.println("Book deleted successfully.");
                return;
            }
        }

        System.out.println("Book not found.");
    }

    private static void searchBook(Scanner scanner) {
        System.out.println("Search options:");
        System.out.println("1. Search by title");
        System.out.println("2. Search by author");
        System.out.println("3. Search by category");

        System.out.print("Enter search option: ");
        int searchOption = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Enter search query: ");
        String query = scanner.nextLine();

        switch (searchOption) {
            case 1:
                searchByTitle(query);
                break;
            case 2:
                searchByAuthor(query);
                break;
            case 3:
                searchByCategory(query);
                break;
            default:
                System.out.println("Invalid search option.");
        }
    }

    private static void searchByTitle(String query) {
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(query.toLowerCase())) {
                System.out.println(book);
            }
        }
    }

    private static void searchByAuthor(String query) {
        for (Book book : books) {
            if (book.getAuthor().toLowerCase().contains(query.toLowerCase())) {
                System.out.println(book);
            }
        }
    }

    private static void searchByCategory(String query) {
        for (Book book : books) {
            if (book.getCategory().toLowerCase().contains(query.toLowerCase())) {
                System.out.println(book);
            }
        }
    }

    private static void viewAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books found.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    private static void changePassword(Scanner scanner) {
        System.out.print("Enter old password: ");
        String oldPassword = scanner.nextLine();

        if (oldPassword.equals(currentPassword)) {
            System.out.print("Enter new password: ");
            String newPassword = scanner.nextLine();
            currentPassword = newPassword;
            System.out.println("Password changed successfully.");
        } else {
            System.out.println("Incorrect old password. Password change failed.");
        }
    }

    private static void exitProgram() {
        System.out.println("Exiting program in 3 seconds...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}

class Book {
    private String title;
    private String author;
    private String category;

    public Book(String title, String author, String category) {
        this.title = title;
        this.author = author;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public String toString() {
        return "Title: " + title + ", Author: " + author + ", Category: " + category;
    }
}
