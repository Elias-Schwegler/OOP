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
    private Items items = new Items();
    private double maxWeight = 1;
    private int moves = 0;
    private int maxMoves = 6;
    private Room beamTarget;
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
    public void enterRoom(Room room) 
    {
        moves++;
        currentRoom = room;
    }
    
    /**
     * Gets the room in which the player is currently located.
     */
    public Room getCurrentRoom() 
    {
        return currentRoom;
    }
    
    /**
     * Get the name of the player.
     */
    public String getName() 
    {
        return name;
    }
    
    /**
     * Returns a string describing the items that the player carries.
     */
    public String getItemsString() 
    {
        return "You are carrying: " + items.getLongDescription();
    }
    
    /**
     * Returns a string describing the players current location and which
     * items the player carries.
     */
    public String getLongDescription() 
    {       
        String returnString = currentRoom.getLongDescription();
        returnString += "\n" + getItemsString();
        return returnString;
    }
    

    /**
     * Tries to pick up the item from the current room.
     * 
     * @return If successfull this method will return the item that was picked up.
     */
    public Item pickUpItem(String itemName) 
    {
        if(canPickItem(itemName)) {
            Item item = currentRoom.removeItem(itemName);
            items.put(item.getName(), item);            
            return item;
        } else {
            return null;
        }
    }
    
    /**
     * Tries to drop an item into the current room.
     * 
     * @return If successfull this method will return the item that was dropped.
     */
    public Item dropItem(String itemName) 
    {
        Item item = items.remove(itemName);
        if(item != null) {
            currentRoom.addItem(item);            
        }
        return item;
    }
    
    /**
     * Eats the item if possible.
     */
    public Item eat(String itemName) 
    {
        if(itemName.equals("cookie")) {
            //First see if we have a cookie in our inventory
            Item cookie = items.get(itemName);
            //Then check if there is a cookie in the room
            if(cookie == null) { 
                cookie = currentRoom.removeItem(itemName);
            }
            if(cookie != null) {
                maxWeight += 1;
                return cookie;    
            }
        }
        return null;
    }
    
    /**
     * Checks if we can pick up the item. This depends on whether the item 
     * actually is in the current room and if it is not too heavy.
     */
    private boolean canPickItem(String itemName) 
    {
        boolean canPick = true;
        Item item = currentRoom.getItem(itemName);
        if(item == null) {
            canPick = false;
        }
        double totalWeight = items.getTotalWeight() + item.getWeight();
        if(totalWeight > maxWeight) {
            canPick = false;
        }
        return canPick;         
    }
    
    /**
     * Checks if the player is dead. 
     * The player dies when he has exceeded some number of moves.
     */
    public boolean isDead() 
    {
        return moves > maxMoves;
    }     
    
    /**
     * Charger the beamer to the current room
     */
    public void chargeBeamer() 
    {
        beamTarget = currentRoom;
    }
    
    /**
     * Fires the beamer
     */
    public boolean fireBeamer() 
    {
        if(beamTarget != null) {
            enterRoom(beamTarget);
            return true;
        }
        return false;
    }    
}