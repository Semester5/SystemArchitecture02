package imaging.filter;

import pmp.filter.DataTransformationFilter2;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;

import javax.media.jai.PlanarImage;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.security.InvalidParameterException;

public class RegionOfInterestFilter extends DataTransformationFilter2<PlanarImage, PlanarImage> {

    private int x;
    private int y;
    private int width;
    private int height;

    public RegionOfInterestFilter(int x, int y, int width, int height, Readable<PlanarImage> input, Writeable<PlanarImage> output) throws InvalidParameterException {
        super(input, output);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public RegionOfInterestFilter(int x, int y, int width, int height, Readable<PlanarImage> input) throws InvalidParameterException {
        super(input);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public RegionOfInterestFilter(int x, int y, int width, int height, Writeable<PlanarImage> output) throws InvalidParameterException {
        super(output);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


    @Override
    protected PlanarImage process(PlanarImage entity) {
        Rectangle rectangle = new Rectangle(x, y, width, height);
        return PlanarImage.wrapRenderedImage((RenderedImage) entity.getAsBufferedImage(rectangle, entity.getColorModel()));
    }
}
