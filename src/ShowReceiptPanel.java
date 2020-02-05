import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * This panel has a text area to diplay the receipt for the user.
 * If the user clicks the simple button it will show the simple receipt for this user
 * If the user clicks the comprehensive button it will show a comprehensive receipt for the user
 * @author Chanip Chong, William Barrera
 * @version 1.0 12/8/2018
 */
public class ShowReceiptPanel extends JPanel
{
    SystemDisplay topFrame = (SystemDisplay)SwingUtilities.getWindowAncestor(this);
    Receipt receipt;
    JTextArea text;


    /**
     * Constructor for the receipt visualization panel
     */
    public ShowReceiptPanel()
    {
        receipt=new Receipt();
        text = new JTextArea();

        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout());

        setLayout(new BorderLayout());
        JButton simple = new JButton("Simple Receipt");
        simple.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displaySimpleReceipt();
                text.repaint();
            }
        });

        JButton comprehensive = new JButton("comprehensive receipt");
        comprehensive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayComprehensiveReceipt();
                text.repaint();
            }
        });

        buttons.add(simple);
        buttons.add(comprehensive);

        add(text, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);
    }

    /**
     * Display a simple receipt in the text area
     */
    public void displaySimpleReceipt() {
        SystemDisplay topFrame = (SystemDisplay) SwingUtilities.getWindowAncestor(this);
        text.setText(receipt.SimpleReceiptFormat(topFrame.getReservations(), topFrame.getCurrentUser().getID()));

    }

    /**
     * Display a comprehensive receipt in the text are
     */
    public void displayComprehensiveReceipt() {
        SystemDisplay topFrame = (SystemDisplay) SwingUtilities.getWindowAncestor(this);
        text.setText(receipt.ComprehensiveReceiptFormat(topFrame.getReservations(), topFrame.getCurrentUser().getID()));
    }
}