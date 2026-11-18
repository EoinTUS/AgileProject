
package cinemaBookingSystem;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class CinemaTicketGUI {

    static int availableSeats = 100;
    static int lastSeatsBooked = 0;

    public static void main(String[] args) {

        JFrame frame = new JFrame("Cinema Ticket");
        frame.setSize(450, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(7, 2, 10, 10));

        // Movie ComboBox
        String[] movies = {
            "The Lord of the Rings: The Fellowship of the Ring",
            "The Lord of the Rings: The Two Towers",
            "The Lord of the Rings: The Return of the King",
            "Star Wars Episode 1 The Phantom Menace",
            "Star Wars Episode 2 Attack of The Clones"
        };

        JComboBox<String> movieComboBox = new JComboBox<>(movies);

        // Cinema Locations
        String[] locations = {
            "Vue Liffey Valley",
            "Omniplex Cinema",
            "IMC Cinema"
        };

        JComboBox<String> locationComboBox = new JComboBox<>(locations);

        // Available Seats
        JTextField availableSeatsField =
            new JTextField(String.valueOf(availableSeats));

        availableSeatsField.setEditable(false);

        // Seats to Book
        JTextField seatsToBookField = new JTextField();

        // Email Address
        JTextField emailField = new JTextField();

        // Buttons
        JButton bookButton = new JButton("Book Seats");
        JButton printButton = new JButton("Print Ticket");

        // Ticket Display
        JTextArea ticketArea = new JTextArea();
        ticketArea.setEditable(false);

        // Book Seats Button
        bookButton.addActionListener(e -> {

            try {
                int seatsRequested =
                    Integer.parseInt(seatsToBookField.getText());

                if (seatsRequested <= 0) {
                    JOptionPane.showMessageDialog(
                        frame, "Enter a valid number");
                    return;
                }

                if (seatsRequested > availableSeats) {
                    JOptionPane.showMessageDialog(
                        frame, "Not enough seats available!");
                } else {
                    availableSeats -= seatsRequested;
                    lastSeatsBooked = seatsRequested;

                    availableSeatsField.setText(
                        String.valueOf(availableSeats));
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                    frame, "Enter a number!");
            }
        });

        // Print Ticket Button
        printButton.addActionListener(e -> {

            String email = emailField.getText().trim();

            if (email.isEmpty()) {
                JOptionPane.showMessageDialog(
                    frame, "Please enter an email address!");
                return;
            }

            String selectedMovie =
                (String) movieComboBox.getSelectedItem();

            String selectedLocation =
                (String) locationComboBox.getSelectedItem();

            int seatsBooked;

            try {
                seatsBooked =
                    Integer.parseInt(seatsToBookField.getText());

                if (seatsBooked <= 0) {
                    JOptionPane.showMessageDialog(
                        frame, "Enter a valid number of seats!");
                    return;
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                    frame, "Enter seats first!");
                return;
            }

            Ticket ticket = new Ticket(
                selectedLocation, selectedMovie, seatsBooked);

            ticketArea.setText(ticket.getTicketDetails());

            // Save Ticket to File
            try (BufferedWriter writer =
                new BufferedWriter(new FileWriter("ticket.txt"))) {

                writer.write(ticket.getTicketDetails());

                // Email Confirmation Popup
                JOptionPane.showMessageDialog(
                    frame, "Email sent");

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(
                    frame, "Error saving ticket!");
                ex.printStackTrace();
            }
        });

        // GUI Layout
        frame.add(new JLabel("Select Location:"));
        frame.add(locationComboBox);

        frame.add(new JLabel("Select Movie:"));
        frame.add(movieComboBox);

        frame.add(new JLabel("Available Seats:"));
        frame.add(availableSeatsField);

        frame.add(new JLabel("Seats to Book:"));
        frame.add(seatsToBookField);

        frame.add(new JLabel("Email Address:"));
        frame.add(emailField);

        frame.add(bookButton);
        frame.add(printButton);

        frame.add(new JScrollPane(ticketArea));
        frame.add(new JLabel(""));

        frame.setVisible(true);
    }
}
