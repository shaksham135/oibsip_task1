import java.util.Date;

public class Train {
    private int trainNumber;
    private String origin;
    private String destination;
    private String name;
    private Date departureTime;
    private int totalSeats;
    private int seatsAvailable;

    public Train(int trainNumber, String origin, String destination, String name, Date departureTime, int totalSeats) {
        this.trainNumber = trainNumber;
        this.origin = origin;
        this.destination = destination;
        this.name = name;
        this.departureTime = departureTime;
        this.totalSeats = totalSeats;
        this.seatsAvailable = totalSeats;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getName() {
        return name;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public void bookSeat() {
        seatsAvailable--;
    }

    public void cancelBooking() {
        seatsAvailable++;
    }
}
