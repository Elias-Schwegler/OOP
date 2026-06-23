import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * The Scenario represents the world we move in. It holds all locations
 * we can enter in this game.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2016.02.29
 */
public class Scenario
{
    private List<Room> rooms;     // List of all rooms in the game
    private Room startRoom;
    private Random random;

    /**
     * Create a complete scenario (with all rooms).
     */
    public Scenario()
    {
        Room outside, theatre, pub, lab, office, transporter;
      
        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        transporter = new TransporterRoom("in a strange, dark place", this);
        
        // initialise room exits
        outside.setExit("east", theatre);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theatre.setExit("west", outside);
        theatre.setExit("north", transporter);

        transporter.setExit("south", theatre);
        
        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);

        startRoom = outside;  // start game outside
        
        rooms = new ArrayList<>();
        rooms.add(outside);
        rooms.add(theatre);
        rooms.add(pub);
        rooms.add(lab);
        rooms.add(office);
        rooms.add(transporter);
        
        random = new Random();
    }

    /**
     * Return the room where players start the game.
     */
    public Room getStartRoom()
    {
        return startRoom;
    }
    
    /**
     * Return a random room in this game.
     */
    public Room getRandomRoom()
    {
        return rooms.get(random.nextInt(rooms.size()));
    }
}
