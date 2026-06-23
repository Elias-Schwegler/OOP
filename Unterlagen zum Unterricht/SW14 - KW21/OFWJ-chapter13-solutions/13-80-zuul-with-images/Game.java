
/**
 * Write a description of class Game here.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class Game
{
	private UserInterface ui;
	private GameEngine engine;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
		engine = new GameEngine();
		ui = new VisualInterface(engine);
		engine.setInterface(ui);
    }
    
    /**
     * Play the game.
     */
    public void play()
    {
        ui.takeControl();
    }
}
