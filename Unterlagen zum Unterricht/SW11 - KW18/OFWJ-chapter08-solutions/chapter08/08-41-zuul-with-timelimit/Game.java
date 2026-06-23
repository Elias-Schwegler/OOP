/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Player player;
    private Room outside, theatre, pub, lab, office;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {        
        player = new Player("Zaphod");
        Room startRoom = createRooms();        
        player.enterRoom(startRoom); // start game outside
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     * 
     * @return Returns the starting room
     */
    private Room createRooms()
    {
      
        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        
        // put items in the room
        pub.addItem(new Item("beer", "a tasty beer", 0.5));
        pub.addItem(new Item("wine", "a dusty bottle of old wine", 0.75));
        lab.addItem(new Item("computer", "a brand new computer", 30));
        theatre.addItem(new Item("cookie", "a magic cookie", 0.1));
        
        // initialise room exits
        outside.setExit("east", theatre);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theatre.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);
        
        return outside;
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
            if(player.isDead()) {
                printDead();
                finished = true;
            }
            if(player.getCurrentRoom() == office) {
                printVictory();
                finished = true;
            }
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(player.getLongDescription());
    }
    
    private void printDead() 
    {
        System.out.println("\nYou lost the game.");
    }
    
    private void printVictory() 
    {
        System.out.println("\nYou won the game. Congratulations!");
    }
    
    
    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        } 
        else if (commandWord.equals("take")) {
            take(command);
        }
        else if (commandWord.equals("drop")) {
            drop(command);
        }        
        else if (commandWord.equals("items")) {
            printItems();
        }
        else if (commandWord.equals("eat")) {
            eat(command);
        }
        
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            player.enterRoom(nextRoom);
            System.out.println(player.getLongDescription());
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game. Return true, if this command
     * quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    /** 
     * Try to take an item from the current room. If the item is there,
     * pick it up, if not print an error message.
     */
    private void take(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to take...
            System.out.println("What do you want to take?");
            return;
        }

        String itemName = command.getSecondWord();
        Item item = player.pickUpItem(itemName);
        
        if(item == null) {
            System.out.println("Can't pick up the item: " + itemName);
        } 
        else {
            System.out.println("Picked up " + item.getDescription());
        }
    }
    
    /** 
     * Drops an item into the current room. If the player carries the item
     * drop it, if not print an error message.
     */
    private void drop(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to drop...
            System.out.println("What do you want to drop?");
            return;
        }

        String itemName = command.getSecondWord();
        Item item = player.dropItem(itemName);
        
        if(item == null) {
            System.out.println("You don't carry the item: " + itemName);
        } 
        else {
            System.out.println("Dropped " + item.getDescription());
        }
    }
    
    /**
     * Prints out the items that the player is currently carrying.
     */
    private void printItems() 
    {
        System.out.println(player.getItemsString());   
    }
    
     /** 
     * Try to take an item from the current room. If the item is there,
     * pick it up, if not print an error message.
     */
    private void eat(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to eat...
            System.out.println("What do you want to eat?");
            return;
        }
        String itemName = command.getSecondWord();
        Item item = player.eat(itemName);
        if(item == null) {
            System.out.println("Could not eat " + itemName);            
        } 
        else {
            System.out.println("Ate " + item.getDescription());
        }
    }
}
