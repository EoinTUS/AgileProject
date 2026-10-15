package cinemaBookingSystem;

class Ticket extends Cinema {
    private String movie;
    private int seatsBooked;

    public Ticket(String location, String movie, int seatsBooked) {
        super(location);
        this.movie = movie;
        this.seatsBooked = seatsBooked;
    }

    public String getTicketDetails() {
        return "----- CINEMA TICKET -----\n" +
               "Location: " + location + "\n" +
               "Movie: " + movie + "\n" +
               "Seats Booked: " + seatsBooked + "\n" +
               "Enjoy your show!\n" +
               "-------------------------";
    }
}
