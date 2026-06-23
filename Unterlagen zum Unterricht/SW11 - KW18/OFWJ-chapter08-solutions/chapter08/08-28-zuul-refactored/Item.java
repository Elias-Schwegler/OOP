/**
 * Class Item - an item in an adventure game.
 *
 * This class is a modification of the "World of Zuul" application in
 * order to solve exercise 6.28 in Objects First With Java. 
 * 
 * An  "Item" has a description and a weight.
 * 
 * @author  Poul Henriksen
 * @version 2016.02.29
 */
public class Item
{
    // A description of the item.
    private String description;
    // The weight of the item.
    private double weight;
    
    /**
     * Create a new item with the given description and weight.
     */
    public Item(String description, double weight)
    {
        this.description = description;
        this.weight = weight;
    }

    /**
     * Return the item's weight.
     * @return The weight
     */
    public double getWeight()
    {
        return weight;
    }
    
    /**
     * Return a description of the item.
     * @return A description.
     */
    public String getDescription()
    {
        return description;
    }
}
