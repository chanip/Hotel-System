import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * The view for the manager's view by Room panel.
 * This will update whenever a new room is selected.
 * @author Chanip Chong, William Barrera
 * @version 1.0 12/7/2018
 */
public class RoomReservationsView extends JFrame
{
    JTextArea textArea;
    JTextArea reservations;

    /**
     * Constructor for the RoomReservationsView.
     */
    public RoomReservationsView()
    {
        textArea=new JTextArea("These are the reservations for Room ");
        this.setLayout(new BorderLayout());
        this.add(textArea,BorderLayout.NORTH);
        reservations=new JTextArea();

        this.setLocationRelativeTo(null);
        this.setSize(500, 500);
        this.setVisible(true);
        this.add(reservations,BorderLayout.CENTER);
    }

    /**
     * Prints all the reservations in the system for the selected room
     * @param list the list of reservations in the system
     * @param room the selected room number
     */
    public void printReservations(ArrayList<Reservation> list, int room){
        textArea.setText("These are the reservations of the room "+room+":");
        reservations.setText("");
        for (Reservation R:list)
        {
            //between
            if (R.getRoom().getRoomNumber() == room)
            {
                String S="Guest: "+R.getGuest().getFirstName()+","+R.getGuest().getLastName()+"  room: " +R.getRoom().getRoomNumber()+"   Date: "+R.getStartDate()+"  to  "+R.getEndDate() + "\n";
                reservations.append(S);
            }
        }
    }

}