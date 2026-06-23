import java.awt.Color;

/**
 * An image filter to make the image a bit lighter.
 * 
 * @author Michael Kölling and David J. Barnes.
 * @version 1.0
 */
public class LighterFilter extends Filter
{
    // The transformer to darken pixels.
    private PixelTransformer transformer;

    /**
	 * Constructor for objects of class LighterFilter.
     * @param name The name of the filter.
	 */
	public LighterFilter(String name)
    {
        super(name);
        // Create the pixel transformer.
        transformer = new PixelTransformer() {
            public Color apply(Color pixel)
            {
                return pixel.brighter();
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
