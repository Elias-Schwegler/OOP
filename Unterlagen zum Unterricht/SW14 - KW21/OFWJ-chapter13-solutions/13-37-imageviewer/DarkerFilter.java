import java.awt.Color;

/**
 * An image filter to make the image a bit darker.
 * 
 * @author Michael Kölling and David J. Barnes.
 * @version 1.0
 */
public class DarkerFilter extends Filter
{
    // The transformer to darken pixels.
    private PixelTransformer transformer;
    
    /**
     * Constructor for objects of class DarkerFilter.
     * @param name The name of the filter.
     */
    public DarkerFilter(String name)
    {
        super(name);
        // Create the pixel transformer.
        transformer = new PixelTransformer() {
            public Color apply(Color pixel)
            {
                return pixel.darker();
            }
        };
    }

    /**
     * Apply this filter to an image.
     * 
     * @param  image  The image to be changed by this filter.
     */
    public void apply(OFImage image)
    {
        transformAll(image, transformer);
    }
}
