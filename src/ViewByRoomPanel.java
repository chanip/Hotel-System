import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * To present a panel that shows room numbers to let user view reservation by room
 * @author Chanip Chong
 * @version 1.0 12/8/2018
 */
public class ViewByRoomPanel extends JPanel
{
    JPanel top=new JPanel();
    JPanel center=new JPanel();
    JPanel centerR=new JPanel();
    JPanel botR=new JPanel();
    JPanel bot=new JPanel();
    JTextArea textfield=new JTextArea("Room View");
    RoomReservationsView view;

    /**
     *  constructor of ViewByRoomPanel that create the panel
     */
    public ViewByRoomPanel()
    {
        view = new RoomReservationsView();
        setLayout(new BorderLayout());
        this.setLayout(new BorderLayout());
        top.add(textfield);
        center.setLayout(new BorderLayout());
        bot.setLayout(new BorderLayout());
        center.add(new JTextArea("Economic Rooms "),BorderLayout.NORTH);
        bot.add(new JTextArea("Luxurious Rooms "),BorderLayout.NORTH);
        centerR.setLayout(new GridLayout(2, 5));
        botR.setLayout(new GridLayout(2, 5));
        botR.setPreferredSize(new Dimension(100, 200));
        centerR.setPreferredSize(new Dimension(100, 200));
        for (int i = 0; i < 10; i++)
        {
            int label=100+i;
            String room=Integer.toString(label);
            JButton keyButton = new JButton(room);
            keyButton.setPreferredSize(new Dimension(40, 40));
            keyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SystemDisplay topFrame = (SystemDisplay) SwingUtilities.getWindowAncestor(getPanel());
                    topFrame.PrintReservation(label, view);
                }
            });
            centerR.add(keyButton);
        }
        for (int i = 0; i < 10; i++)
        {
            int label=200+i;
            String room=Integer.toString(label);
            JButton keyButton = new JButton(room);
            keyButton.setPreferredSize(new Dimension(40, 40));
            keyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SystemDisplay topFrame = (SystemDisplay) SwingUtilities.getWindowAncestor(getPanel());
                    topFrame.PrintReservation(label, view);
                }
            });
            botR.add(keyButton);
        }
        center.add(centerR,BorderLayout.CENTER);
        bot.add(botR,BorderLayout.CENTER);
        this.add(top, BorderLayout.NORTH);
        this.add(center, BorderLayout.CENTER);
        this.add(bot, BorderLayout.SOUTH);
    }

    /**
     * a function that return the ViewByRoomPanel
     * @return ViewByRoomPanel
     */
    private ViewByRoomPanel getPanel(){
        return this;
    }
}