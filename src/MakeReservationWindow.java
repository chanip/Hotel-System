import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;


/**
 * A JDialog window which allows the Guest to make a reservation
 * @author Chris Albright
 * @version 1.0 12/8/2018
 */
public class MakeReservationWindow extends JDialog
{
    private LocalDate chosenMonth;
    private JComboBox<Integer> inDay;
    private JComboBox<String> inMonth;
    private JComboBox<Integer> inYear;
    private JComboBox<Integer> outDay;
    private JComboBox<String> outMonth;
    private JComboBox<Integer> outYear;
    private JComboBox<String> roomType;
    private SystemDisplay sd;

    /**
     * Sets up the GUI of the window and sets listeners
     * @param sd the SystemDisplay
     */
    public MakeReservationWindow(SystemDisplay sd){
        super((Window)null);

        this.sd = sd;

        setModal(true);
        setLayout(new FlowLayout());

        JButton ok = new JButton("OK");
        JButton cancel = new JButton("CANCEL");
        ok.setBounds(75, 75, 120, 40);

        String[] months = new String[12];				//12 months to choose from
        for(int i = 1; i <= months.length; i++) {
            months[i-1] = LocalDate.of(1, i, 1).getMonth().toString();
        }

        //**********Check-in menus**********
        //Months
        chosenMonth = LocalDate.now();
        inMonth = new JComboBox<String>(months);
        inMonth.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                setMonth(inMonth.getSelectedIndex(), true);
            }
        });

        //Days
        inDay = new JComboBox<Integer>();
        JLabel checkinlabel = new JLabel("Check-in");
        checkinlabel.setLabelFor(inDay);
        for(int i = 0; i < chosenMonth.lengthOfMonth(); i++) {
            inDay.addItem(i+1);
        }

        //Years
        Integer[] years = new Integer[5];				//Allows user to choose 5 years from this year
        for(int i = 0; i < years.length; i++) {
            years[i] = i+LocalDate.now().getYear();
        }
        inYear = new JComboBox<Integer>(years);

        //**********Check-out menus**********
        //Months
        chosenMonth = LocalDate.now();
        outMonth = new JComboBox<String>(months);
        outMonth.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                setMonth(outMonth.getSelectedIndex(), false);
            }
        });

        //Days
        outDay = new JComboBox<Integer>();
        JLabel checkoutlabel = new JLabel("Check-out");
        checkoutlabel.setLabelFor(outDay);
        for(int i = 0; i < chosenMonth.lengthOfMonth(); i++) {
            outDay.addItem(i+1);
        }

        //Years
        Integer[] yearsOut = new Integer[5];				//Allows user to choose 5 years from this year
        for(int i = 0; i < yearsOut.length; i++) {
            yearsOut[i] = i+LocalDate.now().getYear();
        }
        outYear = new JComboBox<Integer>(yearsOut);

        roomType = new JComboBox<String>(new String[]{"Economic Room ($100)", "Luxury Room ($300)"});

        add(checkinlabel);
        add(inDay);
        add(inMonth);
        add(inYear);
        add(checkoutlabel);
        add(outDay);
        add(outMonth);
        add(outYear);
        add(roomType);
        add(ok);
        add(cancel);

        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createReservation();
                dispose();
            }
        });

        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Sets the month
     * @param month the month the user has selected to check-in or check-out
     * @param checkin true if the the user is changing check-in date, false otherwise
     */
    public void setMonth(int month, boolean checkin)
    {
        month++;
        if(checkin) {
            inDay.removeAllItems();
            chosenMonth = LocalDate.of(inYear.getItemAt(inYear.getSelectedIndex()), month, 1);
            for(int i = 0; i < chosenMonth.lengthOfMonth(); i++) {
                inDay.addItem(i+1);
            }
        }else {
            outDay.removeAllItems();
            chosenMonth = LocalDate.of(outYear.getItemAt(outYear.getSelectedIndex()), month, 1);
            for(int i = 0; i < chosenMonth.lengthOfMonth(); i++) {
                outDay.addItem(i+1);
            }
        }
    }

    /**
     * Creates a new Reservation with the specified dates and Room
     * If there are no rooms available the Reservaiton will not be made and the Guest will be notified
     * If the check-in or check-out dates conflict with others the Guest will be notified
     * If the total number of days reserved is over 60 the Guest will be notified
     */
    private void createReservation()
    {
        boolean luxury;
        MessageWindow mw;
        if(roomType.getSelectedIndex() == 1){
            luxury = true;
        }else {
            luxury = false;
        }
        Month selectedInMonth = Month.valueOf(Month.class, (String)inMonth.getSelectedItem());
        Month selectedOutMonth = Month.valueOf(Month.class, (String)outMonth.getSelectedItem());
        LocalDate checkin = LocalDate.of((int)inYear.getSelectedItem(), selectedInMonth, (int)inDay.getSelectedItem());
        LocalDate checkout = LocalDate.of((int)outYear.getSelectedItem(), selectedOutMonth, (int)outDay.getSelectedItem());
        Guest guest = (Guest)sd.getCurrentUser();

        Room room = sd.getAvailableRoom(luxury, checkin, checkout);

        if(Duration.between(checkin.atStartOfDay(), checkout.atStartOfDay()).toDays() > 60){
            mw = new MessageWindow("Cannot reserve rooms for more than 60 days");
            return;
        }else if(checkin.isAfter(checkout)) {
            mw = new MessageWindow("Check-in date cannot be before check-out");
        }

        if(room == null) {
            mw = new MessageWindow("No rooms available");
            return;
        }

        Reservation r = new Reservation(guest, room, checkin, checkout);
        r.setCreatedDate(LocalDate.now());
        sd.addReservation(r);

    }
}