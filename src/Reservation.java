public class Reservation {
    private String pnr;
    private User user;
    private Train train;

    public Reservation(String pnr, User user, Train train) {
        this.pnr = pnr;
        this.user = user;
        this.train = train;
    }

    public String getPnr() {
        return pnr;
    }

    public User getUser() {
        return user;
    }

    public Train getTrain() {
        return train;
    }
}

