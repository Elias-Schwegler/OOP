import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

/**
 * An interface for zuul that provides a GUI.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class VisualInterface implements UserInterface
{
    // The game engine.
    private GameEngine engine;
    // The window.
    private JFrame myFrame;
    // Where the user enters commands.
    private JTextField entryField;
    // An output area.
    private JTextArea log;
    // An image for the current room.
    private JLabel image;
    
    /**
     * Constructor for objects of class VisualInterface
     * @param engine The game engine.
     */
    public VisualInterface(GameEngine engine)
    {
        this.engine = engine;
        createGUI();
    }

    /**
     * Take control of running the game.
     * Should be called once, after setup is complete.
     *  Main play routine.  Loops until end of play.
     */
    public void takeControl()
    {            

        engine.printWelcome();
    }

    
    /**
     * Output a line of text.
     * @param text The text to be output.
     */
    public void println(String text)
    {
        print(text + "\n");
    }
    
    /**
     * Output an end-of-line.
     */
    public void println()
    {
        print("\n");
    }
    
    /**
     * Output a line of text.
     * @param text The text to be output.
     */
    public void print(String text)
    {
        log.append(text);
        log.setCaretPosition(log.getDocument().getLength());
    }
    /**
     * Show an image file in the interface.
     */
    public void showImage(String imageName)
    {
        URL imageURL = this.getClass().getClassLoader().getResource(imageName);
        if(imageURL == null)
            System.out.println("image not found");
        else {
            ImageIcon icon = new ImageIcon(imageURL);
            image.setIcon(icon);
            myFrame.pack();
        }
    }

    /**
     * Enable or disable input in the input field.
     */
    public void enable(boolean on)
    {
        entryField.setEditable(on);
        if(!on)
            entryField.getCaret().setBlinkRate(0);
    }

    /**
     * Set up graphical user interface.
     */
    private void createGUI()
    {
        myFrame = new JFrame("Zuul");
        entryField = new JTextField(34);

        log = new JTextArea();
        log.setEditable(false);
        JScrollPane listScroller = new JScrollPane(log);
        listScroller.setPreferredSize(new Dimension(200, 200));
        listScroller.setMinimumSize(new Dimension(100,100));

        JPanel panel = new JPanel();
        image = new JLabel();

        panel.setLayout(new BorderLayout());
        panel.add(image, BorderLayout.NORTH);
        panel.add(listScroller, BorderLayout.CENTER);
        panel.add(entryField, BorderLayout.SOUTH);

        myFrame.getContentPane().add(panel, BorderLayout.CENTER);

        // add some event listeners to some components
        myFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });

        entryField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
                // No need to check the type of action.
                // There is only one possible action: text entry
                processCommand();
            }
        });

        myFrame.pack();
        myFrame.setVisible(true);
        entryField.requestFocus();
    }


    /**
     * A command has been entered. Read the command and do whatever is 
     * necessary to process it.
     */
    private void processCommand()
    {
        boolean finished = false;
        String input = entryField.getText();
        entryField.setText("");

        engine.interpretCommand(input);
    }
}
