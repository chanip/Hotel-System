import java.time.LocalDate;

/**
 * To present a reservation structure
 * @author Chris Albright, William Barrera, Chanip Chong
 * @version 1.0 12/8/2018
 */
public class Reservation {

    private Guest guest;
    private Room room;
    private LocalDate StartDate;
    private LocalDate EndDate;
    private LocalDate createdDate;

    /**
     * the constructor
     * @param g the guest who make the reservation
     * @param r the room that the reservation keeps
     * @param start the first date to check in
     * @param end the date to check out
     */
    public Reservation(Guest g, Room r, LocalDate start, LocalDate end)
    {
        guest = g;
        room = r;
        StartDate = start;
        EndDate = end;
    }


    /**
     * to get the one who make this reservation
     * @return the one who make this reservation
     */
    public Guest getGuest() {
        return guest;
    }

    /**
     * to get the room in the reservation
     * @return the room in the reservation
     */
    public Room getRoom() {
        return room;
    }

    /**
     * to get the check in date
     * @return the check in date
     */
    public LocalDate getStartDate() {
        return StartDate;
    }

    /**
     * to get the check out date
     * @return the check out date
     */
    public LocalDate getEndDate() {
        return EndDate;
    }

    /**
     *  to converse the information of reservation into one string
     * @return A string that holds the information of reservation
     */
    public String toString()
    {
        return guest + " " + room + " " + StartDate.toString() + " " + EndDate.toString();
    }


    /**
     * to check if two reservation is equal.
     * @param o the other reservation to be compared with
     * @return return true if two reservation is the same
     */
    public boolean equals(Object o)
    {
        Reservation other = (Reservation)o;
        return other.getGuest().equals(guest) && other.getRoom().equals(room) && other.getStartDate().equals(StartDate) && other.getEndDate().equals(EndDate);
    }


    /**
     * To get the date when the reservation made.
     * @return the date when the reservation made.
     */
    public LocalDate getCreatedDate() {
        return createdDate;
    }


    /**
     * to change the date when the reservation made
     * @param r the date that we want reservation made changed to be
     */
    public void setCreatedDate(LocalDate r){
        createdDate = r;
    }
}
