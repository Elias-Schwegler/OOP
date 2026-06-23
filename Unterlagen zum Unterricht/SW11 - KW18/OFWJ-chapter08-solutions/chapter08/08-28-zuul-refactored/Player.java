/**
 * This is the representation of a player in the game Zuul.
 * 
 * @author Poul Henriksen
 * @version 2016.02.29
 */
public class Player
{
    private String name;
    private Room currentRoom;
    
    /**
     * Constructor for objects of class Player
     */
    public Player(String name)
    {
        this.name = name;
    }

    /**
     * Enter the given room.
     */
    public void enterRoom(Room room) {
        currentRoom = room;
    }
    
    /**
     * Gets the room in which the player is currently located.
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }
    
    public String getName() {
        return name;
    }


}