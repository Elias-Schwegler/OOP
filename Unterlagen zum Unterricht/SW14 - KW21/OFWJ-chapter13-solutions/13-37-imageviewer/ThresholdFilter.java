import java.awt.Color;

/**
 * An three-level gray-based threshold filter.
 * 
 * @author Michael Kölling and David J. Barnes.
 * @version 1.0
 */
public class ThresholdFilter extends Filter
{
    // The transformer to darken pixels.
    private PixelTransformer transformer;

    /**
	 * Constructor for objects of class ThresholdFilter.
     * @param name The name of the filter.
	 */
	public ThresholdFilter(String name)
    {
        super(name);
        // Create the pixel transformer.
        transformer = new PixelTransformer() {
            public Color apply(Color pixel)
            {
                int brightness = (pixel.getRed() + pixel.getBlue() + pixel.getGreen()) / 3;
                if(brightness <= 85) {
                    return Color.BLACK;
                }
                else if(brightness <= 170) {
                    return Color.GRAY;
                }
                else {
                    return Color.WHITE;
                }
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
