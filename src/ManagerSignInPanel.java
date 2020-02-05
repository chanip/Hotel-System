import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The JPanel shown to the Manager who wants to sign in
 * @author Willam Barrera
 * @version 1.0 12/8/2018
 */
public class ManagerSignInPanel extends JPanel {

    JLabel l1, l2, title, subText;
    JTextField uName;
    JButton signInBtn;
    JPasswordField uPass;

    /**
     * Sets up GUI and adds listeners
     */
    ManagerSignInPanel() {

        setLayout(null);

        //Initialize Components
        title = new JLabel("Employee Log In");
        subText = new JLabel("Please enter your username and password.");
        l1 = new JLabel("Username");
        l2 = new JLabel("Password");
        uName = new JTextField();
        uPass = new JPasswordField();
        signInBtn = new JButton("Sign In!");

        //Set Bounds and component visuals
        title.setBounds(100, 20, 300, 30);
        title.setForeground(Color.blue);
        title.setFont(new Font("Sans", Font.BOLD, 25));
        subText.setBounds(50, 70, 300, 30);
        l1.setBounds(50, 110, 100, 30);
        l2.setBounds(50, 150, 100, 30);
        uName.setBounds(150, 110, 200, 30);
        uPass.setBounds(150, 150, 200, 30);
        signInBtn.setBounds(150, 210, 100, 30);

        //Add the components to the panel
        add(title);
        add(subText);
        add(l1);
        add(uName);
        add(l2);
        add(uPass);
        add(signInBtn);

        signInBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Collect user inputs from text fields
                String name = uName.getText();
                String pass = new String(uPass.getPassword());
                SystemDisplay topFrame = (SystemDisplay) SwingUtilities.getWindowAncestor(getPanel());

                //if login valid (look through list)
                if(topFrame.checkLogin(name, pass)) {
                    changeToLoggedInWindow();
                }
                else{
                    MessageWindow err = new MessageWindow("Invalid Username or Password.");
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
     * Changes the current panel to the AfterManagerLoginPanel and adds top buttons in the frame
     */
    public void changeToLoggedInWindow(){
        SystemDisplay topFrame = getTopFrame();
        topFrame.setUserButtons();
        changePanel(new AfterManagerLoginPanel());
    }

    /**
     * Returns this panel
     * @return this panel
     */
    private ManagerSignInPanel getPanel(){
        return this;
    }

    /**
     * @return reference to SystemDisplay
     */
    private SystemDisplay getTopFrame(){
        return (SystemDisplay) SwingUtilities.getWindowAncestor(this);
    }
}
