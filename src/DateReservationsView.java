import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;


/**
 * The view for the manager's view by date panel.
 * This will update whenever a new date is selected.
 * @author Chanip Chong, William Barrera
 * @version 1.0 12/7/2018
 */
public class DateReservationsView extends JFrame{

        JTextArea textArea;
        JTextArea reservations;

    /**
     * Constructor for the DateReservationsView class
     */
    public DateReservationsView() {
        textArea = new JTextArea("ROOM INFORMATION FOR ");
        reservations = new JTextArea();

        setLayout(new BorderLayout());
        add(textArea, BorderLayout.NORTH);

        setLocationRelativeTo(null);
        setSize(500, 500);
        setVisible(true);
        add(reservations, BorderLayout.CENTER);
    }

    /**
     * Prints the available and reserved rooms for the selected day.
     * @param list the list of reservations in the system
     * @param rooms the list of rooms in the hotel
     * @param theDate the current selected date
     */
     public void printDayInfo (ArrayList<Reservation> list, ArrayList<Room> rooms, LocalDate theDate) {
        textArea.setText("ROOM INFORMATION FOR " + theDate + ":");
        reservations.setText("");

        String bookedRooms = "";
        String openRooms = "";

        reservations.append("\nAvailable Rooms:\n");

        for (Reservation R : list) {
            //between
            if (R.getStartDate().isBefore(theDate) && R.getEndDate().isAfter(theDate) || R.getStartDate().isEqual(theDate) || R.getEndDate().isEqual(theDate)) {
                bookedRooms += ("Room: " + R.getRoom().getRoomNumber() + " | Guest: " + R.getGuest().getFirstName() + "," + R.getGuest().getLastName() + "\n");
            }
        }

        for (Room R : rooms) {
            if (R.checkAvailability(theDate)) {
                openRooms += "Room: " + R.getRoomNumber() + "\n";
            }
        }

        reservations.append(openRooms);
        reservations.append("\nReserved Rooms:\n\n" + bookedRooms);
    }
}
