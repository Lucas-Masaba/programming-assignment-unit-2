// I imported ArrayList and Scanner for this project. 
import java.util.ArrayList;
import java.util.Scanner;

// LibrarySystem class created.
public class LibrarySystem {
  // ArrayList that only allows Book objects.
  // I chose to use ArrayList because the size can be modified unlike arrays.
  private ArrayList<Book> library = new ArrayList<>();

  // Main method that creates a new LibrarySystem object.
  public static void main(String[] args) {
    LibrarySystem ls = new LibrarySystem();
    ls.run();
  }

  public void run() {
    // Scanner object created to take user input.
    Scanner scanner = new Scanner(System.in);
    // While loop that runs until the user chooses to exit.
    while (true) {
      System.out.println("1. Add Books\n2. Borrow Books\n3. Return Books\n4. List Books\n5. Exit");
      // Try-catch block to handle invalid input instead of breaking the program.
      try {
        // User input is taken and stored in the option variable.
        int option = scanner.nextInt();
        scanner.nextLine(); // consume newline because nextInt() does not consume a newline.
        // Switch statement that calls the appropriate method based on the user's input.
        switch (option) {
          case 1:
            addBook(scanner);
            break;
          case 2:
            borrowBook(scanner);
            break;
          case 3:
            returnBook(scanner);
            break;
          case 4:
            listBooks();
            break;
          case 5:
            System.out.println("Exiting...");
            return;
          default:
            System.out.println("Invalid option. Please try again.");
        }
      } catch (Exception e) {
        System.out.println("Invalid input. Please enter an integer.");
        scanner.nextLine();
      }
    }
  }

  // Method to add a book to the library that takes a Scanner object as a
  // parameter.
  private void addBook(Scanner scanner) {
    // Try-catch block to handle invalid input instead of breaking the program.
    try {
      System.out.println("Enter the book title:");
      String title = scanner.nextLine();
      System.out.println("Enter the author:");
      String author = scanner.nextLine();
      System.out.println("Enter quantity:");
      int quantity = scanner.nextInt();
      scanner.nextLine(); // consume newline
      Book book = findBook(title);
      // If the book is not in the library, it is added. If it is, the quantity is
      // increased.
      if (book == null) {
        library.add(new Book(title, author, quantity));
      } else {
        book.quantity += quantity;
      }
      System.out.println("Book added successfully.");
    } catch (Exception e) {
      System.out.println("Invalid input. Please make sure that quantity is an integer.");
      scanner.nextLine();
    }
  }

  // Method to borrow a book from the library that takes a Scanner object as a
  // parameter.
  private void borrowBook(Scanner scanner) {
    // Try-catch block to handle invalid input instead of breaking the program.
    try {
      System.out.println("Enter book title:");
      String title = scanner.nextLine();
      System.out.println("Enter quantity to borrow:");
      int quantity = scanner.nextInt();
      scanner.nextLine(); // consume newline
      Book book = findBook(title);
      // If the book is not in the library, a message is displayed. If the quantity is less than the quantity the user wants to borrow, a message is displayed. Otherwise, the quantity is decreased.
      if (book == null) {
        System.out.println("The book is not available.");
      } else if (book.quantity < quantity) {
        System.out.println("The number of books you are trying to borrow is not available.");
      } else {
        book.quantity -= quantity;
        System.out.println("Book(s) borrowed successfully.");
      }
    } catch (Exception e) {
      System.out.println("Invalid input. Please make sure that quantity is an integer.");
      scanner.nextLine();
    }
  }
  // Method to return a book to the library that takes a Scanner object as a parameter.
  private void returnBook(Scanner scanner) {
    // Try-catch block to handle invalid input instead of breaking the program.
    try {
      System.out.println("Enter book title:");
      String title = scanner.nextLine();
      System.out.println("Enter quantity to return:");
      int quantity = scanner.nextInt();
      scanner.nextLine(); // consume newline
      Book book = findBook(title);
      // If the book is not in the library, a message is displayed. Otherwise, the quantity is increased.
      if (book == null) {
        System.out.println("This book does not belong to our library.");
      } else {
        book.quantity += quantity;
        System.out.println("Book returned successfully.");
      }
    } catch (Exception e) {
      System.out.println("Invalid input. Please make sure that quantity is an integer.");
      scanner.nextLine();
    }
  }
  // Method to find a book in the library that takes a title as a parameter.
  private Book findBook(String title) {
    for (Book book : library) {
      if (book.title.equals(title)) {
        return book;
      }
    }
    return null;
  }
  // Method to list all the books in the library. I added this to check if the other methods are working correctly.
  private void listBooks() {
    System.out.println("List of books:");
    // For loop to iterate through the library ArrayList.
    for (Book book : library) {
      System.out.println(book.title + " by " + book.author + " (" + book.quantity + ")");
    }
  }
  // Book class created to store the title, author, and quantity of a book.
  private static class Book {
    String title;
    String author;
    int quantity;

    Book(String title, String author, int quantity) {
      this.title = title;
      this.author = author;
      this.quantity = quantity;
    }
  }
}