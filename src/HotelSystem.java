import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Data model for the reservation system
 * Stores Room, User, and Reservation objects
 * Reads and writes to the users and reservation txt file
 * @author Chris Albright, William Barrera, Chanip Chong
 * @version 1.0 12/8/2018
 */
public class HotelSystem {

    ArrayList<Room> rooms = new ArrayList<>();
    ArrayList<Manager> managers;
    ArrayList<Guest> guests;
    ArrayList<Reservation> reservations;

    /**
     * Constructor of HotelSystem
     * Populates the luxuryRooms and economicRooms arrays with their corresponding numbers
     * Initializes and populates the ArrayLists except for reservations, which is done by a manager
     * Prints out users in the system
     */
    HotelSystem()
    {
        //Initialize Rooms
        for (int i = 0; i < 10; i++)
        {
            rooms.add(new LuxuryRoom(i + 200));
            rooms.add(new EconomicRoom(i + 100));
        }
        Collections.sort(rooms);

        //Initialize Guests, Mangers and reservations lists
        managers = new ArrayList<Manager>();
        guests = new ArrayList<Guest>();
        reservations = new ArrayList<Reservation>();

        populateUsers();
    }

    /**
     * Reads form the users.txt file and fills the guest and manager ArrayList
     * according to the user type
     */
    public void populateUsers()
    {
        try {
            File f = new File("users.txt");
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line = br.readLine();
            while(line != null)
            {
                String[] data = line.split(":");
                if(data[5].equalsIgnoreCase("guest")){
                    guests.add(new Guest(data[0], data[1], data[2], data[3], data[4]));
                }else if(data[5].equalsIgnoreCase("manager")){
                    managers.add(new Manager(data[0], data[1], data[2], data[3], data[4]));
                }line = br.readLine();

            }br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads form the reservations.txt file and fills the reservations ArrayList
     */
    public void populateReservations()
    {
        int count = 0;
        try {
            File f = new File("reservations.txt");
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line = br.readLine();
            while(line != null)
            {
                String[] data = line.split(":");
                Guest g = getGuest(data[0]);		//Finds the guest who made reservation
                if(g == null)						//If the guest in the reservation is not in the system, the loop will skip that reservation and print out error message
                {
                    line = br.readLine();
                    continue;
                }
                Room r = getRoom(Integer.parseInt(data[1]));	//Finds room that was reserved

                LocalDate s = LocalDate.parse(data[2], DateTimeFormatter.ofPattern("u-M-d"));
                LocalDate e = LocalDate.parse(data[3], DateTimeFormatter.ofPattern("u-M-d"));

                //Check if reservation exists or overlaps before adding to the system
                Reservation newRes = new Reservation(g, r, s, e);
                if(reservations.contains(newRes))
                {
                    line = br.readLine();
                    continue;
                }

                LocalDate created = LocalDate.parse(data[4], DateTimeFormatter.ofPattern("u-M-d"));
                newRes.setCreatedDate(created);
                reservations.add(newRes);

                //Add Set Reserved Days for the room
                LocalDate cur = LocalDate.parse(data[2], DateTimeFormatter.ofPattern("u-M-d"));
                ArrayList<LocalDate> reservedDays = new ArrayList<>();
                while(cur.isBefore(e) || cur.isEqual(e)) {
                    reservedDays.add(cur);
                    cur = cur.plusDays(1);
                }
                r.addReservedDate(reservedDays);

                count++;
                line = br.readLine();
            }br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        MessageWindow mw = new MessageWindow(count + ": reservations loaded");
    }

    /**
     * Returns the reservations in the system
     * @return the reservations ArrayList
     */
    public ArrayList<Reservation> getReservations()
    {
        return reservations;
    }

    /**
     * Gets the guest with the specified first name
     * @param ID the ID of the guest with this reservation
     * @return the guest whose ID matches with the given ID
     */
    public Guest getGuest(String ID) {
        for(Guest g : guests){
            if(g.getID().equalsIgnoreCase(ID))
                return g;
        }return null;
    }

    /**
     * Gets the appropriate room with the parsed roomNumber
     * @param roomNumber the number of the room to be found
     * @return the room that matches the provided number
     */
    public Room getRoom(int roomNumber) {
        for(Room r : rooms) {
            if(r.getRoomNumber() == roomNumber)
                return r;
        }

        return null;
    }

    /**
     * Returns the rooms in the system
     * @return an ArrayList containing the rooms in the system
     */
    public ArrayList<Room> getRooms(){
        return rooms;
    }

    /**
     *
     * @param luxury True if the room is luxury, false if it is economic
     * @param ci The requested check-in date
     * @param co The requested check-out date
     * @return an available luxury or economic room or null if none are available
     */
    public Room getAvailableRoom(boolean luxury, LocalDate ci, LocalDate co) {
        if(luxury) {
            for(Room r : rooms) {
                if(r.checkAvailability(ci, co) && r.getType().equals("Luxury")) {
                    return r;
                }
            }
        }else {
            for(Room r : rooms) {
                if(r.checkAvailability(ci, co) && r.getType().equals("Economic")) {
                    return r;
                }
            }
        }return null;
    }

    /**
     * Checks if the guest with the given username and password exists
     * @param username the username of the guest
     * @param password the password of the guest
     * @return true if the guest exists the guests ArrayList, false if not
     */
    public boolean checkGuest(String username, String password) {
        for(Guest g : guests) {
            if(g.getUserName().equals(username) && g.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the guest with the specified username exists
     * @param username the username of the guest
     * @return true if the guest exists the guests ArrayList, false if not
     */
    public boolean checkGuest(String username) {
        for(Guest g : guests) {
            if(g.getUserName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the manager with the given username and password exists
     * @param username the username of the manager
     * @param password the password of the manager
     * @return true if the manager exists the managers ArrayList, false if not
     */
    public boolean checkManager(String username, String password) {
        for(Manager m : managers) {
            if(m.getUserName().equals(username) && m.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Adds the specified Guest to the guests ArrayList and stores them into the users.txt file
     * @param g the Guest to add
     */
    public void addGuest(Guest g) {
        guests.add(g);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("users.txt", true));
            bw.newLine();
            bw.append(g.getFirstName() + ":" + g.getLastName() + ":" +  g.getID() + ":" +  g.getUserName() + ":" +  g.getPassword() + ":" + g.getType());
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the User with the correct username
     * @param username the username of the User
     * @return the specified user
     */
    public User getUser(String username) {
        for(Guest g : guests) {
            if(g.getUserName().equals(username)) {
                return g;
            }
        }

        for(Manager m : managers) {
            if(m.getUserName().equals(username)) {
                return m;
            }
        }
        return null;
    }

    /**
     * Adds the specified Reservation to the reservations ArrayList
     * Also adds the days of reservation to the room specified in the Reservation
     * @param r the Reservation to add
     */
    public void addReservation(Reservation r)
    {
        reservations.add(r);
        LocalDate cur = r.getStartDate();
        LocalDate end = r.getEndDate();
        ArrayList<LocalDate> reservedDays = new ArrayList<>();
        while(cur.isBefore(end) || cur.isEqual(end)) {
            reservedDays.add(cur);
            cur = cur.plusDays(1);
        }
        Room room = r.getRoom();
        room.addReservedDate(reservedDays);
    }

    /**
     * Writes all of the Reservations in the reservations ArrayList to the reservations.txt file
     */
    public void saveReservations(){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("reservations.txt", false));
            for(Reservation r : reservations){
                bw.newLine();
                bw.append(r.getGuest().getID() + ":" + r.getRoom().getRoomNumber() + ":" + r.getStartDate() + ":" + r.getEndDate() + ":" + r.getCreatedDate());
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}