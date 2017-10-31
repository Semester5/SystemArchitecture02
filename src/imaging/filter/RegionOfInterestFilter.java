package imaging.filter;

import pmp.filter.DataTransformationFilter2;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;

import javax.media.jai.PlanarImage;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.security.InvalidParameterException;

public class RegionOfInterestFilter extends DataTransformationFilter2<PlanarImage, PlanarImage> {

    public RegionOfInterestFilter(Readable<PlanarImage> input, Writeable<PlanarImage> output) throws InvalidParameterException {
        super(input, output);
    }


    @Override
    protected PlanarImage process(PlanarImage entity) {
        Rectangle rectangle = new Rectangle(0, 0, 500, 170);
        return PlanarImage.wrapRenderedImage((RenderedImage) entity.getAsBufferedImage(rectangle, entity.getColorModel()));
    }
}
