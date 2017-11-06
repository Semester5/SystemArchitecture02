package imaging.filter;

import pmp.filter.DataTransformationFilter2;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;

import javax.media.jai.ImageLayout;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.RenderedOp;
import javax.media.jai.operator.MedianFilterDescriptor;
import java.awt.*;
import java.security.InvalidParameterException;

public class MedianFilter extends DataTransformationFilter2<PlanarImage, PlanarImage> {

    public MedianFilter(Readable<PlanarImage> input, Writeable<PlanarImage> output) throws InvalidParameterException {
        super(input, output);
    }

    public MedianFilter(Readable<PlanarImage> input) throws InvalidParameterException {
        super(input);
    }

    public MedianFilter(Writeable<PlanarImage> output) throws InvalidParameterException {
        super(output);
    }

    @Override
    protected PlanarImage process(PlanarImage entity) {
        RenderedOp renderableOp =  MedianFilterDescriptor.create(entity, MedianFilterDescriptor.MEDIAN_MASK_SQUARE, 6, null);
        return renderableOp;
    }
}
