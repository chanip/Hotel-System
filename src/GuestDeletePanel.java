import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.time.temporal.ChronoUnit;

/**
 * Displays the reservations for the current logged in user and has buttons for the user to cancel them.
 * @author Chanip Chong
 * @version 1.0 12/7/2018
 */
public class GuestDeletePanel extends JPanel
{

    /**
     * Constructor for the GuestDeletePanel
     * @param R The list of reservations in the system
     * @param G the current user
     */
    public GuestDeletePanel(ArrayList<Reservation> R,User G)
    {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        int size=R.size();

        for (int i=0; i<size;i++)
        {
            if (R.get(i).getGuest().getID()==G.getID())
            {
                long daysBetween = ChronoUnit.DAYS.between(R.get(i).getStartDate(), R.get(i).getEndDate());
                double total = R.get(i).getRoom().getRate()*(daysBetween);
                JPanel panel=new JPanel();
                JTextArea textArea=new JTextArea();
                String re= "Room " + R.get(i).getRoom() + "\n" +R.get(i).getRoom().getType()+"  $"+R.get(i).getRoom().getRate()+"/day\n"+"period: "+R.get(i).getStartDate()+" - "+R.get(i).getEndDate()+"\n total $: "+total+"\n\n\n";
                textArea.setText(re);
                JButton cancel=new JButton("cancel");
                panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
                int finalI = i;
                cancel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        delete(finalI);
                    }
                });
                panel.add(textArea);
                panel.add(cancel);
                this.add(panel);
            }
        }

    }

    /**
     * Deletes the selected reservation.
     * This calls the SystemDisplay's cancelReservation method.
     * @param i The number of the reservation to delete.
     */
    public void delete(int i)
    {
        SystemDisplay topFrame = (SystemDisplay) SwingUtilities.getWindowAncestor(this);
        topFrame.cancelReservation(i);
        topFrame.setCurrentPanel(new AfterGuestLoginPanel());
    }

}