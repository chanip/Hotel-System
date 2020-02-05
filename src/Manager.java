/**
 * A user with the type of "manager"
 * These users can access manager functions when they log in as manager
 * @author William Barrera and Chong Chanip
 * @version 1.0 12/8/2018
 */
public class Manager extends User {

    Manager(String f, String l, String id, String uN, String p){
        super(f, l, id, uN, p, "Manager");
    }
}
