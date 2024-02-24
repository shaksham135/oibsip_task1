import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ReservationSystem reservationSystem = new ReservationSystem();
        Scanner scanner = new Scanner(System.in);
        Train selectedTrain = null;

        // Prompt user for login
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        // Attempt login
        if (reservationSystem.login(username, password)) {
            System.out.println("Login successful. Welcome to the Online Train Reservation System");

            // Main menu options
            while (true) {
                System.out.println("1. Search Trains");
                System.out.println("2. Book a Ticket");
                System.out.println("3. Cancel Reservation");
                System.out.println("4. Logout");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter origin: ");
                        String origin = scanner.next();
                        System.out.print("Enter destination: ");
                        String destination = scanner.next();
                        List<Train> availableTrains = reservationSystem.searchTrains(origin, destination);
                        if (!availableTrains.isEmpty()) {
                            System.out.println("Available trains from " + origin + " to " + destination + ":");
                            for (Train train : availableTrains) {
                                System.out.println("Train Number: " + train.getTrainNumber());
                                System.out.println("Train Name: " + train.getName());
                                System.out.println("Departure Time: " + new SimpleDateFormat("dd-MM-yyyy HH:mm").format(train.getDepartureTime()));
                                System.out.println("Available Seats: " + train.getSeatsAvailable() + "/" + train.getTotalSeats());
                                System.out.println();
                            }
                        } else {
                            System.out.println("No trains available for the selected route.");
                        }
                        break;
                    case 2:
                        System.out.print("Enter train number: ");
                        int trainNumber = scanner.nextInt();
                        selectedTrain = reservationSystem.getTrainByNumber(trainNumber);
                        if (selectedTrain != null) {
                            System.out.println("Train Name: " + selectedTrain.getName());
                            System.out.println("Departure Time: " + new SimpleDateFormat("dd-MM-yyyy HH:mm").format(selectedTrain.getDepartureTime()));
                            System.out.println("Available Seats: " + selectedTrain.getSeatsAvailable() + "/" + selectedTrain.getTotalSeats());
                            System.out.print("Confirm reservation (yes/no): ");
                            String confirm = scanner.next();
                            if (confirm.equalsIgnoreCase("yes")) {
                                String pnr = reservationSystem.makeReservation(selectedTrain);
                                if (pnr != null) {
                                    System.out.println("Ticket booked successfully. Your PNR is: " + pnr);
                                } else {
                                    System.out.println("Failed to book ticket. Please try again later.");
                                }
                            } else {
                                System.out.println("Reservation cancelled.");
                            }
                        } else {
                            System.out.println("Invalid train number.");
                        }
                        break;
                    case 3:
                        System.out.print("Enter PNR number to cancel reservation: ");
                        String pnrToCancel = scanner.next();
                        boolean cancellationResult = reservationSystem.cancelReservationByPnr(pnrToCancel);
                        if (cancellationResult) {
                            System.out.println("Reservation with PNR " + pnrToCancel + " cancelled successfully.");
                        } else {
                            System.out.println("Failed to cancel reservation. Invalid PNR number.");
                        }
                        break;
                    case 4:
                        reservationSystem.logout();
                        System.out.println("Logged out successfully.");
                        System.exit(0);
                    case 5:
                        System.out.println("Thank you for using the Online Train Reservation System. Goodbye!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Invalid username or password. Exiting...");
        }
    }
}
