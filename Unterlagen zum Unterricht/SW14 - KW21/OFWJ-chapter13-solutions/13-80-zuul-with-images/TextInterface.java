import java.util.Scanner;

/**
 * An interface for zuul that outputs text to
 * the standard output terminal.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class TextInterface implements UserInterface
{
    // The game engine.
    private GameEngine engine;
    // The source of command input
    private Scanner reader;         
    
    /**
     * Constructor for objects of class TextInterface.
     * @param engine The game engine.
     */
    public TextInterface(GameEngine engine)
    {
        this.engine = engine;
        this.reader = new Scanner(System.in);
    }

    /**
     * Take control of running the game.
     * Should be called once, after setup is complete.
     *  Main play routine.  Loops until end of play.
     */
    public void takeControl()
    {            

        engine.printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            // print prompt
            print("> ");
            String inputLine = reader.nextLine();
            finished = engine.interpretCommand(inputLine);
        }
    }

    
    /**
     * Output a line of text.
     * @param text The text to be output.
     */
    public void println(String text)
    {
        System.out.println(text);
    }
    
    /**
     * Output an end-of-line.
     */
    public void println()
    {
        System.out.println();
    }
    
    /**
     * Output a line of text.
     * @param text The text to be output.
     */
    public void print(String text)
    {
        System.out.print(text);
    }

    
    /**
     * Show an image.
     * No image is shown in the text interface.
     * @param imageFile The image file.
     */
    public void showImage(String imageFile)
    {
    }
}
