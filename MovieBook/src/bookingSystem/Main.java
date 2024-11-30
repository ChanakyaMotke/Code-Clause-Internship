package bookingSystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BookingSystem system = new BookingSystem();
        Scanner scanner = new Scanner(System.in);

        // Add sample movies
        system.addMovie(new Movie("Inception", "6:00 PM", 10));
        system.addMovie(new Movie("Interstellar", "9:00 PM", 8));
        system.addMovie(new Movie("Tenet", "7:30 PM", 12));

        while (true) {
            System.out.println("\n1. View Movies");
            System.out.println("2. Book Ticket");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    system.displayMovies();
                    break;

                case 2:
                    system.displayMovies();
                    System.out.print("Enter the movie number: ");
                    int movieIndex = scanner.nextInt() - 1;

                    if (movieIndex >= 0 && movieIndex < system.getMovies().size()) {
                        System.out.print("Enter the seat number to book: ");
                        int seatNumber = scanner.nextInt();

                        system.bookTicket(movieIndex, seatNumber);
                    } else {
                        System.out.println("Invalid movie selection.");
                    }
                    break;

                case 3:
                    System.out.println("Thank you for using the ticket booking system!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
