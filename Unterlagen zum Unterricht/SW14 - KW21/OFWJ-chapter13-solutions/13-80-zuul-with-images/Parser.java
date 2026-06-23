import java.util.Scanner;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This parser tries to interpret input text as an "Adventure"
 * command.
 * It tries to interpret a line as a two word command.
 * It returns the command as an object of class Command.
 *
 * The parser has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class Parser 
{
    // holds all valid command words
    private CommandWords commands;  

    /**
     * Create a parser.
     */
    public Parser() 
    {
        commands = new CommandWords();
    }

    /**
     * Parse the given input line.
     * @param inputLine The line to be parsed.
     * @return The next command from the user.
     */
    public Command getCommand(String inputLine) 
    {
        String word1 = null;
        String word2 = null;

        // Find up to two words on the line.
        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next();      // get first word
            if(tokenizer.hasNext()) {
                word2 = tokenizer.next();      // get second word
                // note: we just ignore the rest of the input line.
            }
        }

        // Now check whether this word is known. If so, create a command
        // with it. If not, create a "null" command (for unknown command).
        if(commands.isCommand(word1)) {
            return new Command(word1, word2);
        }
        else {
            return new Command(null, word2); 
        }
    }


    /**
     * Return a list of all valid commands.
     * @return The valid commands, as a string.
     */
    public String getCommands()
    {
        return commands.getCommands();
    }
}
