/**
 * A room which has a rate of 100
 * @author William Barrera, Chanip Chong
 * @version 1.0 12/8/2018
 */
public class EconomicRoom extends Room {

    EconomicRoom(int n){
        super(n, 100.00, "Economic");
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