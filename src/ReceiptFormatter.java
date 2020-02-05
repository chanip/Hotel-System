/**
 * Interface for receipt formatter which will format a receipt in different ways.
 * @author Chong, Chanip @version 1.0 12/8/2018
 */
public interface ReceiptFormatter
{
    /**
     * Returns the contents of the receipt as a string formatted
     * in the corresponding way.
     * @param R one reservation in the system
     * @return one string that holds name, room, price, period
     */
    String information(Reservation R);

    /**
     *  Returns a string holding the total cost of the reservation.
     * @return a total amount for one reservation
     */
    String totalmoney();
}