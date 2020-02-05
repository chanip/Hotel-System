/**
 * A user with the type of "guest"
 * These users can access guest functions when they log in as guest
 * @author William Berrara and Chong Chanip
 * @version 1.0 11/26/2018
 */
public class Guest extends User{

    Guest(String f, String l, String id, String uN, String p){
        super(f, l, id, uN, p, "Guest");
    }

}
