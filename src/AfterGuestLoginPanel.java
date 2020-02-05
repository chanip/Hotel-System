import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


/**
 * The panel which is displayed to the guest after they login
 * Contains buttons to allow user to make a reservation or view/cancel a reservation
 * @author Chris Albright and William Barrera
 * @version 1.0 12/8/2018
 */
public class AfterGuestLoginPanel extends JPanel
{
    /**
     * Sets up the GUI of the JPanel and adds listeners to the buttons
     */
    public AfterGuestLoginPanel() {
        setLayout(null);
        JButton make = new JButton("Make a reservation");
        JButton viewReceipt = new JButton("View Receipt");
        JButton viewcancel = new JButton("View/Cancel reservation");

        make.setBounds(100, 50, 200, 30);
        make.setBackground(Color.LIGHT_GRAY);
        viewReceipt.setBounds(100, 90, 200, 30);
        viewReceipt.setBackground(Color.LIGHT_GRAY);
        viewcancel.setBounds(100, 130, 200, 30);
        viewcancel.setBackground(Color.LIGHT_GRAY);

        make.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MakeReservationWindow mrw = new MakeReservationWindow(getTopFrame());
            }
        });

        viewReceipt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePanel(new ShowReceiptPanel());
            }
        });

        viewcancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePanel(new GuestDeletePanel(getTopFrame().getReservations(), getTopFrame().getCurrentUser()));
            }
        });

        this.add(make);
        this.add(viewReceipt);
        this.add(viewcancel);
    }

    /**
     * Replaces itself with a new panel based on which button was clicked
     * @param p the panel that will replace this one.
     */
    public void changePanel(JPanel p){
        SystemDisplay topFrame = (SystemDisplay) SwingUtilities.getWindowAncestor(this);
        topFrame.setCurrentPanel(p);
    }


    /**
     * Gets the JFrame this JPanel is displayed on, which is the SystemDisplays
     * @return SystemDisplay
     */
    private SystemDisplay getTopFrame(){
        return (SystemDisplay) SwingUtilities.getWindowAncestor(this);
    }
}