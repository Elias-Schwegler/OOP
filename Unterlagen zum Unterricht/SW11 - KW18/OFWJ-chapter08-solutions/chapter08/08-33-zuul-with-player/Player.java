/**
 * This is the representation of a player in the game Zuul.
 * 
 * @author Poul Henriksen
 * @version 2016.02.29
 */

public class Player
{
    // The player's name.
    private String name;
    // The room the player is in.
    private Room currentRoom;
    // The item's the player is holding.
    private Items items = new Items();
    // The maximum weight the player can hold.
    private double maxWeight;
    
    /**
     * Constructor for objects of class Player
     * @param name The player's name
     */
    public Player(String name)
    {
        this.name = name;
        this.maxWeight = 1;
    }

    /**
     * Enter the given room.
     * @param room The room entered.
     */
    public void enterRoom(Room room)
    {
        currentRoom = room;
    }
    
    /**
     * Gets the room in which the player is currently located.
     * @return The current room.
     */
    public Room getCurrentRoom()
    {
        return currentRoom;
    }
    
    /**
     * Get the name of the player.
     * @return The player's name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Returns a string describing the items that the player carries.
     * @return A description of the items held.
     */
    public String getItemsString()
    {
        return "You are carrying: " + items.getLongDescription();
    }
    
    /**
     * Returns a string describing the players current location and which
     * items the player carries.
     * @return A description of the room and items held.
     */
    public String getLongDescription()
    {       
        String returnString = currentRoom.getLongDescription();
        returnString += "\n" + getItemsString();
        return returnString;
    }
    

    /**
     * Tries to pick up the item from the current room.
     * @param itemName The item to be picked up.
     * @return If successful this method will return the item that was picked up.
     */
    public Item pickUpItem(String itemName)
    {
        if(canPickItem(itemName)) {
            Item item = currentRoom.removeItem(itemName);
            items.put(itemName, item);            
            return item;
        } 
        else {
            return null;
        }
    }
    
    /**
     * Tries to drop an item into the current room.
     * @param itemName The item to be dropped.
     * 
     * @return If successful this method will return the item that was dropped.
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
     * Only cookies can be eaten.
     * @param itemName The item to be eaten.
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
     * Checks if we can pick up the given item. This depends on whether the item 
     * actually is in the current room and if it is not too heavy.
     * @parem itemName The item to be picked up.
     * @return true if the item can be picked up, false otherwise.
     */
    private boolean canPickItem(String itemName)
    {
        boolean canPick = true;
        Item item = currentRoom.getItem(itemName);
        if(item == null) {
            canPick = false;
        }
        else {
            double totalWeight = items.getTotalWeight() + item.getWeight();
            if(totalWeight > maxWeight) {
                canPick = false;
            }
        }
        return canPick;         
    }
       
}