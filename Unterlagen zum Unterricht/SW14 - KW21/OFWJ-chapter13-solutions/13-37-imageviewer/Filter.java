/**
 * Filter is an abstract superclass for all image filters in this
 * application. Filters can be applied to OFImages by invoking the apply 
 * method.
 * 
 * @author Michael Kölling and David J. Barnes.
 * @version 1.0
 */
public abstract class Filter
{
    private String name;

    /**
     * Create a new filter with a given name.
     * @param name The name of the filter.
     */
    public Filter(String name)
    {
        this.name = name;
    }
    
    /**
     * Return the name of this filter.
     * 
     * @return  The name of this filter.
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Apply this filter to an image.
     * 
     * @param  image  The image to be changed by this filter.
     */
    public abstract void apply(OFImage image);
    
    /**
     * Transform each pixel individually.
     * @param image The image to be changed by this filter.
     * @param transform The transformation to be applied.
     */
    public void transformAll(OFImage image, PixelTransformer transform)
    {
        int height = image.getHeight();
        int width = image.getWidth();
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                image.setPixel(x, y, transform.apply(image.getPixel(x, y)));
            }
        }
    }
}
