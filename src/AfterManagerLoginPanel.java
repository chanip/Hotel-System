import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The panel which is displayed to the manager after they login
 * Contains buttons to allow user to view, load, and save reservations
 * They can also quit the system
 * @author Chris Albright and William Barrera
 * @version 1.0 12/8/2018
 */
public class AfterManagerLoginPanel extends JPanel {


    /**
     * Sets up GUI of the panel and adds listeners to the buttons
     */
    public AfterManagerLoginPanel() {
        setLayout(null);
        JButton view = new JButton("view information");
        JButton load = new JButton("load existing reservations");
        JButton save = new JButton("save reservations");
        JButton quit = new JButton("QUIT");

        view.setBounds(100, 50, 200, 30);
        view.setBackground(Color.LIGHT_GRAY);
        load.setBounds(100, 90, 200, 30);
        load.setBackground(Color.LIGHT_GRAY);
        save.setBounds(100, 130, 200, 30);
        save.setBackground(Color.LIGHT_GRAY);
        quit.setBounds(100, 250, 200, 30);
        quit.setBackground(Color.RED);
        quit.setForeground(Color.WHITE);

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SystemDisplay sd = getTopFrame();
                sd.saveReservations();
            }
        });
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadReservations();
            }
        });
        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePanel(new ManagerViewInfoPanel());
            }
        });
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SystemDisplay sd = getTopFrame();
                sd.saveReservations();
                sd.dispose();
            }
        });


        this.add(view);
        this.add(load);
        this.add(save);
        this.add(quit);
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
     * Calls the loadReservations method of SystemDisplay
     */
    public void loadReservations(){
        SystemDisplay topFrame = (SystemDisplay)SwingUtilities.getWindowAncestor(this);
        topFrame.loadReservations();
    }

    /**
     * Gets reference to the SystemDisplay
     * @return SystemDisplay
     */
    private SystemDisplay getTopFrame(){
        return (SystemDisplay) SwingUtilities.getWindowAncestor(this);
    }

}