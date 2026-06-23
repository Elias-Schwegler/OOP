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
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class GameEngine 
{
    // The user interface.
    private UserInterface ui;
    // The input parser.
    private Parser parser;
    // The current room.
    private Room currentRoom;
        
    /**
     * Create the game and initialise its internal map.
     */
    public GameEngine() 
    {
        createRooms();
        parser = new Parser();
    }
    
    /**
     * Print out the opening message for the player.
     */
    public void printWelcome()
    {
        ui.println();
        ui.println("Welcome to the World of Zuul!");
        ui.println("World of Zuul is a new, incredibly boring adventure game.");
        ui.println("Type 'help' if you need help.");
        ui.println();
        ui.println(currentRoom.getLongDescription());
        ui.showImage(currentRoom.getImage());
    }

    /**
     * Set the user interface.
     * @param ui The user interface.
     */
    public void setInterface(UserInterface ui)
    {
        this.ui = ui;
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theater, pub, lab, office;
      
        // create the rooms
        outside = new Room("outside the main entrance of the university", "images/outside.gif");
        theater = new Room("in a lecture theater", "images/castle.gif");
        pub = new Room("in the campus pub", "images/courtyard.gif");
        lab = new Room("in a computing lab", "images/stairs.gif");
        office = new Room("in the computing admin office", "images/dungeon.gif");
        
        // initialise room exits
        outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theater.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);

        currentRoom = outside;  // start game outside
    }

    /**
     * Given command input, interpret (that is: execute) the command.
     * @param commandLine The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    public boolean interpretCommand(String commandLine) 
    {
        Command command = parser.getCommand(commandLine);
        
        // Whether to quit following the command.
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            ui.println("I don't know what you mean...");
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
        // else command not recognised.
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
        ui.println("You are lost. You are alone. You wander");
        ui.println("around at the university.");
        ui.println();
        ui.println("Your command words are:");
        ui.println(parser.getCommands());
    }

    /** 
     * Try to in to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            ui.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            ui.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            ui.println(currentRoom.getLongDescription());
            ui.showImage(currentRoom.getImage());
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            ui.println("Quit what?");
            return false;
        }
        else {
            ui.println("Thank you for playing.  Good bye.");
            return true;  // signal that we want to quit
        }
    }
}
