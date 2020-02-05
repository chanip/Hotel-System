import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The main frame for the program. It will hold the current panel that is being used
 * and will change accordingly to any panel changes. When this frame closes, the program will
 * terminate.
 * @author Chris Albright, William Barrera, Chainp Chong
 * @version 1.0 12/8/2018
 */
public class SystemDisplay extends JFrame
{
    private JPanel currentPanel;
    private JPanel previousPanel;
    private HotelSystem hs;
    private User currentUser;
    private boolean loggedIn;

    /**
     * A constructor for SystemDisplay to initialize the frame
     */
    public SystemDisplay()
    {
        currentPanel = new UserSelectionPanel();
        add(currentPanel);
        hs = new HotelSystem();
        loggedIn = false;

        setVisible(true);
        setTitle("The Group3 Hotel");
        setSize(400,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Sets a panel to be displayed
     * @param panel the panel to display
     */
    public void setCurrentPanel(JPanel panel)
    {
        remove(currentPanel);
        previousPanel = currentPanel;
        currentPanel = panel;
        if(loggedIn) {
            add(currentPanel, BorderLayout.CENTER);
        }
        else add(currentPanel);
        revalidate();
        repaint();
    }

    /**
     * Sets an upper panel with a back and logout button
     */
    public void setUserButtons(){
        loggedIn = true;
        setLayout(new BorderLayout());

        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout());
        JButton back = new JButton("<-");
        JButton signOut = new JButton("Sign Out");

        //Create and attach Listeners
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setCurrentPanel(previousPanel);
            }
        });

        signOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loggedIn = false;
                remove(buttons);
                setCurentUser(null);
                setCurrentPanel(new UserSelectionPanel());
            }
        });

        buttons.add(back);
        buttons.add(signOut);
        add(buttons, BorderLayout.NORTH);
    }

    /**
     * Checks if the user account is valid
     * @param username the username of the user
     * @param password the password of the user
     * @return the result whether the username and password match the account or not
     */
    public boolean checkLogin(String username, String password)
    {
        if(hs.checkGuest(username, password)){
            return true;
        }else if(hs.checkManager(username, password)){
            return true;
        }return false;
    }

    /**
     * To call checkGuest in hotel system and see if the giving is matching one of the name in system
     * @param name input name to be check
     * @return true if there exist same name in the system
     */
    public boolean checkNewGuest(String name){
        return hs.checkGuest(name);
    }

    /**
     * to add a guest user into the system
     * @param g a guest to add into the system
     */
    public void addGuest(Guest g){
        hs.addGuest(g);
    }

    /**
     * to Load Reservations from txt file
     */
    public void loadReservations(){
        hs.populateReservations();
    }

    /**
     * to pass reservations to the viewbyday panel and display the reservations in given date.
     * @param d date:reservations in this date
     * @param view a panel that's going to be on the top of the frame.
     */
    public void PrintReservation(LocalDate d, DateReservationsView view)
    {
        view.printDayInfo(hs.getReservations(), hs.getRooms(), d);
    }

    /**
     * to pass reservations to the viewbyroom panel and display the reservations in given room number.
     * @param room room number: reservations in this room number
     * @param view a panel that's going to be on the top of the frame.
     */
    public void PrintReservation(int room, RoomReservationsView view)
    {
        view.printReservations(hs.getReservations(), room);
    }

    /**
     * to cancel one reservation in the arraylist of reservations
     * @param num the postion in the arraylist the going to be removed
     */
    public void cancelReservation(int num){
        hs.reservations.remove(num);
    }

    /**
     * to get the whole reservations in the hotel system
     * @return arraylist of reservations in the hotel system
     */
    public ArrayList<Reservation> getReservations(){
        return hs.reservations;
    }

    /**
     * return the current user
     * @return current User
     */
    public User getCurrentUser()
    {
        return currentUser;
    }

    /**
     * To set the current user to the given newUser
     * @param newUser a user going to be changed to current user
     */
    public void setCurentUser(User newUser)
    {
        currentUser = newUser;
    }

    /**
     * To return the user with the given username
     * @param username the username that going to be searched
     * @return return a user
     */
    public User getUser(String username)
    {
        return hs.getUser(username);
    }

    /**
     * To return the available room in given type and date
     * @param luxury the type of the room
     * @param ci checkin date
     * @param co checkout date
     * @return the room that's available
     */
    public Room getAvailableRoom(boolean luxury, LocalDate ci, LocalDate co)
    {
        return hs.getAvailableRoom(luxury, ci, co);
    }

    /**
     * To add one reservation into the system
     * @param r the reservation which is going  to add
     */
    public void addReservation(Reservation r)
    {
        hs.addReservation(r);
    }

    /**
     * Tells the hotel system to export reservations to the reservations.txt file
     */
    public void saveReservations(){
        hs.saveReservations();
    }
}