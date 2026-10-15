package cinemaBookingSystem;

public class CinemaTicketGUI {
	static int availableSeats = 100; // starting seats
    static int lastSeatsBooked = 0;
    
    public static void main(String[] args) {
    	JFrame frame = new JFrame("Cinema Ticket");
        frame.setSize(450, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(6, 2, 10, 10));
		
    	// ComboBoxes
        String[] movies = {
            "The Lord of the Rings: The Fellowship of the Ring",
            "The Lord of the Rings: The Two Towers",
            "The Lord of the Rings: The Return of the King"
        };
        JComboBox<String> movieComboBox = new JComboBox<>(movies);
    	
    	
    }
      
}
