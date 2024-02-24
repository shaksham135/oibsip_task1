import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationSystem {
    private List<User> users;
    private List<Train> trains;
    private List<Reservation> reservations;
    private User currentUser;

    public ReservationSystem() {
        users = new ArrayList<>();
        trains = new ArrayList<>();
        reservations = new ArrayList<>();

        // Sample users initialization
        users.add(new User("user1", "password1"));
        users.add(new User("user2", "password2"));

        // Sample trains initialization
        trains.add(new Train(101, "jaipur", "patna", "Satabdi Express", new Date(), 50));
        trains.add(new Train(102, "delhi", "haridwar", "DH5", new Date(), 50));
    }

    // Method to handle user login
    public boolean login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentUser = user;
                return true; // Login successful
            }
        }
        return false; // Login failed
    }

    // Method to handle user logout
    public void logout() {
        currentUser = null;
    }

    // Method to search for trains based on origin and destination
    public List<Train> searchTrains(String origin, String destination) {
        List<Train> availableTrains = new ArrayList<>();
        for (Train train : trains) {
            if (train.getOrigin().equalsIgnoreCase(origin) && train.getDestination().equalsIgnoreCase(destination) && train.getSeatsAvailable() > 0) {
                availableTrains.add(train);
            }
        }
        return availableTrains;
    }

    // Method to book a ticket and return the PNR number
    public String makeReservation(Train train) {
        if (train.getSeatsAvailable() > 0) {
            String pnr = generatePNR(); // Generate PNR number
            Reservation reservation = new Reservation(pnr, currentUser, train);
            reservations.add(reservation);
            train.bookSeat();
            return pnr; // Return the generated PNR number
        } else {
            return null; // Reservation failed (no seats available)
        }
    }

    // Method to cancel a reservation by PNR number
    public boolean cancelReservationByPnr(String pnr) {
        for (Reservation reservation : reservations) {
            if (reservation.getPnr().equals(pnr)) {
                Train train = reservation.getTrain();
                reservations.remove(reservation);
                train.cancelBooking();
                return true; // Cancellation successful
            }
        }
        return false; // Reservation with given PNR not found
    }

    // Method to retrieve a reservation by PNR number
    public Reservation getReservationByPnr(String pnr) {
        for (Reservation reservation : reservations) {
            if (reservation.getPnr().equals(pnr)) {
                return reservation;
            }
        }
        return null; // Reservation with given PNR not found
    }

    // Method to generate a unique PNR number
    private String generatePNR() {
        // Implement your logic to generate a unique PNR number
        return "PNR" + System.currentTimeMillis(); // Example: Using current timestamp as PNR
    }

    // Method to get train by train number
    public Train getTrainByNumber(int trainNumber) {
        for (Train train : trains) {
            if (train.getTrainNumber() == trainNumber) {
                return train;
            }
        }
        return null; // Train not found
    }
}
