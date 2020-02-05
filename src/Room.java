import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The view for the manager's view by Room panel.
 * This will update whenever a new room is selected.
 * @author William Barrera, Chris Albright, Chanip Chong
 * @version 1.0 12/8/2018
 */
public abstract class Room implements Comparable<Room> {

    private final int number;
    private final double rate;
    private String type;
    private ArrayList<LocalDate> reservedDays;

    /**
     * Constructor for the Room class
     * @param num the number of the room
     * @param r the rate of the room
     * @param t the type of room (luxury or economic)
     */
    Room(int num, double r, String t){
        number = num;
        rate = r;
        type = t;
        reservedDays = new ArrayList<LocalDate>();
    }

    /**
     * Returns the room number
     * @return the room number
     */
    public int getRoomNumber(){return number;}

    /**
     * Returns the rate of the room
     * @return the rate for this room
     */
    public double getRate(){return rate;}

    /**
     * Checks if the room is available between the check in and check out date
     * @param ci the check in date
     * @param co the check out date
     * @return true if the room is available, false otherwise
     */
    public boolean checkAvailability(LocalDate ci, LocalDate co){
        if(reservedDays.contains(ci) || reservedDays.contains(co)){
            return false;
        }
        else{ return true;}
    }

    /**
     * Checks if the room is available on the provided date
     * @param d the date to check
     * @return true if the room is available, false otherwise
     */
    public boolean checkAvailability(LocalDate d){
        if(reservedDays.contains(d)){
            return false;
        }
        else{ return true;}
    }

    /**
     * Returns the type of the room
     * @return the type of the room
     */
    public String getType(){return type;}

    /**
     * Sets the room as unavailable for the days in the parameter
     * @param days an ArrayList of days that the room is booked and unavailable
     */
    public void addReservedDate(ArrayList<LocalDate> days){reservedDays.addAll(days);}

    /**
     * Compares this room to another room based on room number
     * @param r the room to compare with
     * @return negative number if the passed room is greater, 0 if they are equal, positive number if this room is greater
     */
    public int compareTo(Room r) {
        return getRoomNumber() - r.getRoomNumber();
    }

    /**
     * Checks if the provided room is the same as this room
     * @param o the object to compare with
     * @return true if the provided room is the same as this room
     */
    public boolean equals(Object o)
    {
        Room other = (Room)o;
        return other.getRoomNumber() == number;
    }

    /**
     * Returns a string with this room's number
     * @return this rooms number as a string
     */
    public String toString(){
        return Integer.toString(number);
    }
}
