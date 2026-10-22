package cinemaBookingSystem;
import javax.swing.*;
import java.awt.*;
import java.io.*;

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
            "The Lord of the Rings: The Return of the King",
			"Star Wars Episode 1 The Phantom Menace",
			"Star Wars Episode 2 Attack of The Clones"
        };
        JComboBox<String> movieComboBox = new JComboBox<>(movies);
		
		//Locations of cinema's
    	String[] locations = {
                "Vue Liffey Valley",
                "Omniplex Cinema",
                "IMC Cinema"
            };
        JComboBox<String> locationComboBox = new JComboBox<>(locations);
    	
     // Seats display
        JTextField availableSeatsField = new JTextField(String.valueOf(availableSeats));
        availableSeatsField.setEditable(false);

        // Input for booking seats
        JTextField seatsToBookField = new JTextField();
        JButton bookButton = new JButton("Book Seats");
        JButton printButton = new JButton("Print Ticket");
        JTextArea ticketArea = new JTextArea();
        ticketArea.setEditable(false);

        //Book Seats button
        bookButton.addActionListener(e -> {
            try {
                int seatsRequested = Integer.parseInt(seatsToBookField.getText());

                if (seatsRequested <= 0) {
                    JOptionPane.showMessageDialog(frame, "Enter a valid number");
                    return;
                }

                if (seatsRequested > availableSeats) {
                    JOptionPane.showMessageDialog(frame, "Not enough seats available!");
                } else {
                    availableSeats -= seatsRequested;
                    availableSeatsField.setText(String.valueOf(availableSeats));
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Enter a number!");
            }
        });
        
        //  Print Ticket button
        printButton.addActionListener(e -> {

            String selectedMovie = (String) movieComboBox.getSelectedItem();
            String selectedLocation = (String) locationComboBox.getSelectedItem();

            int seatsBooked = 0;

            try {
                seatsBooked = Integer.parseInt(seatsToBookField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Enter seats first!");
                return;
            }

            Ticket ticket = new Ticket(selectedLocation, selectedMovie, seatsBooked);

            ticketArea.setText(ticket.getTicketDetails());

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("ticket.txt"))) {
                writer.write(ticket.getTicketDetails());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        
     // Layout (label left, input right)
        frame.add(new JLabel("Select Location:")); frame.add(locationComboBox);      
        frame.add(new JLabel("Select Movie:")); frame.add(movieComboBox);       
        frame.add(new JLabel("Available Seats:")); frame.add(availableSeatsField);
        frame.add(new JLabel("Seats to Book:")); frame.add(seatsToBookField);        
        frame.add(bookButton); frame.add(printButton);
        frame.add(new JScrollPane(ticketArea));
        frame.add(new JLabel(""));
        frame.setVisible(true);
    }
      
}
