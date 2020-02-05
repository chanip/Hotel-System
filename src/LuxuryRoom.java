/**
 * A room which has a rate of 300
 * @author William Barrera, Chanip Chong
 * @version 1.0 12/8/2018
 */
public class LuxuryRoom extends Room {

    LuxuryRoom(int n){
        super(n, 300.00, "Luxury");
    }

    /**
     * Compares this room to another
     * @param r the room to compare with
     * @return negative if this room is smaller, 0 if they are equal, positive if this room is larger
     */
    @Override
    public int compareTo(Room r) {
        return super.getRoomNumber() - r.getRoomNumber();
    }
}