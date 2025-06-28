import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your username: ");
        String username = sc.nextLine();
        System.out.print("Enter role (admin/student): ");
        String role = sc.nextLine();

        User user = new User(username, role);

        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. View Books");
            if (user.getRole().equalsIgnoreCase("admin")) {
                System.out.println("2. Add Book");
                System.out.println("3. Remove Book");
            } else {
                System.out.println("2. Borrow Book");
                System.out.println("3. Return Book");
            }
            System.out.println("0. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    library.displayBooks();
                    break;
                case 2:
                    if (user.getRole().equalsIgnoreCase("admin")) {
                        System.out.print("Enter book ID: ");
                        String id = sc.nextLine();
                        System.out.print("Enter title: ");
                        String title = sc.nextLine();
                        System.out.print("Enter author: ");
                        String author = sc.nextLine();
                        library.addBook(new Book(id, title, author, true));
                    } else {
                        System.out.print("Enter book ID to borrow: ");
                        String id = sc.nextLine();
                        library.borrowBook(id);
                    }
                    break;
                case 3:
                    if (user.getRole().equalsIgnoreCase("admin")) {
                        System.out.print("Enter book ID to remove: ");
                        String id = sc.nextLine();
                        library.removeBook(id);
                    } else {
                        System.out.print("Enter book ID to return: ");
                        String id = sc.nextLine();
                        library.returnBook(id);
                    }
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }

        System.out.println("Goodbye!");
        sc.close();
    }
}
