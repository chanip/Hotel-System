import java.util.ArrayList;

/**
 * To present a user abstract class that holds user's information
 * @author Chris Albright, William Barrera, Chanip Chong
 * @version 1.0 12/8/2018
 */
public abstract class User {

   private String fName;
   private String lName;
   private String id;
   private String userName;
   private String pass;
   private String type;
   public ArrayList<Reservation> reservations;

    /**
     * Constructor for User
     * @param f first name of User
     * @param l last name of User
     * @param userID userID of User
     * @param uN user name of User
     * @param p password of User
     * @param t type of User
     */
   User(String f, String l, String userID, String uN, String p, String t){
       fName = f;
       lName = l;
       id = userID;
       userName = uN;
       pass = p;
       type = t;
       reservations = new ArrayList<Reservation>();
   }

    /**
     * To convert the user information to String
     * @return information of user in String
     */
   public String toString(){
       return type + "[" + fName + ", " + lName + ", " + id + ", " + userName + ", " + pass + "]";
   }

    /**
     * to return first name of user
     * @return first name of user
     */
   public String getFirstName(){return fName;}

    /**
     * to return last name of user
     * @return last name of user
     */
   public String getLastName(){return lName;}

    /**
     * to return the ID of user
     * @return the ID of user
     */
   public String getID(){return id;}

    /**
     * to return the user name of the user
     * @return user name of the user
     */

   public String getUserName(){return userName;}

    /**
     * to return the Password of the user
     * @return Password of the user
     */
   public String getPassword(){return pass;}

    /**
     * to return the type of user
     * @return  the type of user
     */
   public String getType(){return type;}

    /**
     * to check if the two user are equal
     * @param o the user that's going to compare with
     */
    public boolean equals(Object o)
    {
        User other = (User)o;
        return other.id == id;
    }
}
