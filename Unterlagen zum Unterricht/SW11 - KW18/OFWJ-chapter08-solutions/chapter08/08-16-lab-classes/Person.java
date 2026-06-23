
/**
 * The Person class represents a person in an administration system.
 * It holds the name of a person.
 * 
 * @author Poul Henriksen
 * @version 2016.02.29
 */
public class Person
{
    // the ID of the person
    private String id;
  
    // the person's full name
    private String name;
   
    /**
     * Constructor for objects of class Person
     */
    public Person(String name, String id)
    { 
        this.name = name; 
        this.id = id;
    }
    
       /**
     * Return the full name of this person.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Set a new name for this person
     */
    public void changeName(String replacementName)
    {
        name = replacementName;
    }
  
    /**
     * Return the ID of this person.
     */
    public String getID()
    {
        return id;
    }
    
    /**
     * Return the login name of this student. The login name is a combination
     * of the first four characters of the student's name and the first three
     * characters of the student's ID number.
     */
    public String getLoginName()
    {
        return name.substring(0,4) + id.substring(0,3);
    }
    
    /**
     * Print the student's name and ID number to the output terminal.
     */
    public void print()
    {
        System.out.println(name + " (" + id + ")");
    }

}
