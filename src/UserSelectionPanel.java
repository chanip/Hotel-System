import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * To present a UserSelectionPanel that let the user choose to be guest mode or manager mode
 * @author Chris Albright, William Barrera, Chanip Chong
 * @version 1.0 12/8/2018
 */
public class UserSelectionPanel extends JPanel
{
    /**
     *  A constructor to initial a UserSelectionPanel to have 2 button for user to select
     */
    public UserSelectionPanel()
    {
        setLayout(null);

        JButton GuestButton = new JButton("Guest");
        GuestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swapToGuestPanel();
            }
        });

        JButton ManagerButton = new JButton("Manager");
        ManagerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swapToManagerPanel();
            }
        });

        JLabel title = new JLabel("The Team 3 Hotel");
        JLabel subText = new JLabel("Please select your user type");

        //Set Bounds and component visuals
        title.setBounds(90, 30, 250, 30);
        title.setForeground(Color.blue);
        title.setFont(new Font("Sans", Font.BOLD, 25));
        subText.setBounds(120, 80, 220, 30);
        GuestButton.setBounds(100, 160, 200, 30);
        GuestButton.setBackground(Color.LIGHT_GRAY);
        ManagerButton.setBounds(100, 220, 200, 30);
        ManagerButton.setBackground(Color.LIGHT_GRAY);

        add(title);
        add(subText);
        add(GuestButton);
        add(ManagerButton);
    }

    /**
     * To change current panel to GuestSelectionPanel
     */
    public void swapToGuestPanel()
    {
        SystemDisplay topFrame = (SystemDisplay)SwingUtilities.getWindowAncestor(this);
        topFrame.setCurrentPanel(new GuestSelectionPanel());
    }

    /**
     * To change current panel to ManagerSignInPanel
     */
    public void swapToManagerPanel()
    {
        SystemDisplay topFrame = (SystemDisplay)SwingUtilities.getWindowAncestor(this);
        topFrame.setCurrentPanel(new ManagerSignInPanel()); //this should be manager sign in but I made it viewbyday for now to test
    }
}