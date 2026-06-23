
/**
 * An interface for the user interface of zuul.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public interface UserInterface
{
    /**
     * Output a line of text.
     * @param text The text to be output.
     */
    public void println(String text);
    
    /**
     * Output an end-of-line.
     */
    public void println();
    
    /**
     * Output a line of text.
     * @param text The text to be output.
     */
    public void print(String text);
    
    /**
     * Take control of running the game.
     * Should be called once, after setup is complete.
     */
    public void takeControl();
    
    /**
     * Show an image, if available.
     */
    public void showImage(String imageFile);
}
