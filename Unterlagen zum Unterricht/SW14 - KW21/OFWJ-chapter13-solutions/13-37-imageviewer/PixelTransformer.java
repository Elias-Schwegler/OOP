import java.awt.Color;

/**
 * Implementations of this interface are able to apply
 * a transformation to a single pixel.
 * 
 * @author Michael Kölling and David J. Barnes.
 * @version 2016.02.29
 */
public interface PixelTransformer
{
    /**
     * Transform the given pixel.
     * @param pixel The pixel to be transformed.
     * @return The transformed pixel.
     */
    public Color apply(Color pixel);
}
