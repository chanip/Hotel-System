import java.time.LocalDate;
import java.util.ArrayList;

/**
 *  it is a class to hold two method of printing a receipt.
 * @author Chong, Chanip @version 1.0 12/8/2018
 */
public class Receipt
{
    private SimpleReceiptFormat S;
    private ComprehensiveReceiptFormat C;

    private String receipt;
    private int total;

    public Receipt()
    {
        S=new SimpleReceiptFormat();
        C=new ComprehensiveReceiptFormat();
    }

    /**
     * to find out the current user's  Simple Receipt and store it as a String
     * @param R complete Reservations in the system
     * @param userid current user that log in to the system
     * @return a complete simple receipt including price and period, room and name
     */
    public String SimpleReceiptFormat (ArrayList<Reservation> R, String userid)
    {
        LocalDate d=LocalDate.now();
        receipt="";
        for (Reservation reservation:R)
        {
            if (reservation.getGuest().getID()==userid && reservation.getCreatedDate().isEqual(d))
            {
                receipt+=S.information(reservation)+S.totalmoney();
                total+=S.getTotal();
            }

        }
        receipt+="The total amount for all reservations is : $"+total;
        total=0;
        return receipt;
    }

    /**
     * to find out the current user's  Comprehensive Receipt and store it as a String
     * @param R complete Reservations in the system
     * @param userid current user that log in to the system
     * @return a complete Comprehensive receipt including price and period, room and name
     */
    public String ComprehensiveReceiptFormat (ArrayList<Reservation> R, String userid)
    {
        LocalDate d=LocalDate.now();
        receipt="";
        for (Reservation reservation:R)
        {
            if (reservation.getGuest().getID()==userid )
            {
                receipt+=C.information(reservation)+C.totalmoney();
                total+=C.getTotal();
            }

        }
        receipt+="The total amount for all reservations is :"+total;
        total=0;
        return receipt;
    }
}