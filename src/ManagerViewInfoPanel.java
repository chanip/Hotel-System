import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Presents two options for the Manager:
 * - View by Day
 * - View by Room
 * @author Chanip Chong, William Barrera
 * @version 1.0 12/8/2018
 */
public class ManagerViewInfoPanel extends JPanel
{
    /**
     * Constructor for the Manager View Info Panel
     */
    public ManagerViewInfoPanel()
    {
        setLayout(null);
        JButton viewbyday =new JButton("view by day" );
        JButton viewbyroom=new JButton("view by room");

        viewbyday.setBounds(100, 100, 200, 50);
        viewbyday.setBackground(Color.LIGHT_GRAY);
        viewbyroom.setBounds(100, 160, 200, 50);
        viewbyroom.setBackground(Color.LIGHT_GRAY);

        viewbyday.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePanel(new ViewByDayPanel());
            }
        });

        viewbyroom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePanel(new ViewByRoomPanel());
            }
        });

        this.add(viewbyday);
        this.add(viewbyroom);
    }

    /**
     * Replaces itself with a new panel based on which button was clicked
     * @param p the panel that will replace this one.
     */
    public void changePanel(JPanel p){
        SystemDisplay topFrame = (SystemDisplay) SwingUtilities.getWindowAncestor(this);
        topFrame.setCurrentPanel(p);
    }
}