import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * The JPanel shown when the user decides to sign up as a new Guest
 * @author William Barrera
 * @version 1.0 12/8/2018
 */
public class GuestSignUpPanel extends JPanel{

    JLabel l1, l2, l3, l4, title;
    JTextField fName, lName, uName;
    JButton signUpBtn;
    JPasswordField uPass;

    /**
     * Sets up GUI of panel and adds listeners
     */
    GuestSignUpPanel() {

        setLayout(null);

        //Initialize Components
        title = new JLabel("Please enter desired username and password.");
        l1 = new JLabel("First Name");
        l2 = new JLabel("Last Name");
        l3 = new JLabel("Username");
        l4 = new JLabel("Password");
        fName = new JTextField();
        lName = new JTextField();
        uName = new JTextField();
        uPass = new JPasswordField();
        signUpBtn = new JButton("Sign Up!");

        //Set Bounds and component visuals
        title.setBounds(50, 50, 300, 30);
        l1.setBounds(50, 110, 100, 30);
        l2.setBounds(50, 150, 100, 30);
        l3.setBounds(50, 190, 100, 30);
        l4.setBounds(50, 230, 100, 30);
        fName.setBounds(150, 110, 200, 30);
        lName.setBounds(150, 150, 200, 30);
        uName.setBounds(150, 190, 200, 30);
        uPass.setBounds(150, 230, 200, 30);
        signUpBtn.setBounds(150, 290, 100, 30);
        signUpBtn.setBackground(new Color(0,200,0));

        //Add the components to the panel
        add(title);
        add(l1);
        add(uName);
        add(l2);
        add(uPass);
        add(l3);
        add(fName);
        add(l4);
        add(lName);
        add(signUpBtn);

        signUpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Collect user inputs from text fields
                String usrName = uName.getText();
                String first = fName.getText();
                String last = lName.getText();
                String pass = new String(uPass.getPassword());
                SystemDisplay topFrame = (SystemDisplay) SwingUtilities.getWindowAncestor(getPanel());

                //Check
                if(usrName.isEmpty() || first.isEmpty() || last.isEmpty() || pass.isEmpty()){
                    MessageWindow err = new MessageWindow("Please fill all fields");
                }
                else if(!topFrame.checkNewGuest(usrName)) {	//if user doesn't already exist
                    topFrame.addGuest(new Guest(first, last, Integer.toString(calcRandNum()),usrName, pass));	//Create new user and add to HotelSytem arraylist
                    changePanel(new GuestSignInPanel());
                }
                else{
                    MessageWindow err = new MessageWindow("Username already exists");
                }
            }
        });
    }

    /**
     * Replaces itself with a new panel based on which button was clicked
     * @param p the panel that will replace this one.
     */
    public void changePanel(JPanel p){
        SystemDisplay topFrame = getTopFrame();
        topFrame.setCurrentPanel(p);
    }

    /**
     * Returns this panel
     * @return this panel
     */
    private GuestSignUpPanel getPanel(){
        return this;
    }

    /**
     * @return reference to SystemDisplay
     */
    private SystemDisplay getTopFrame(){
        return (SystemDisplay) SwingUtilities.getWindowAncestor(this);
    }

    /**
     * Calculates a random number to assign as a new guest's ID
     * @return the random ID number
     */
    private int calcRandNum(){
        Random rand = new Random();
        return rand.nextInt(999999) + 100000;
    }
}
