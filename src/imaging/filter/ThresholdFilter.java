package imaging.filter;

import pmp.filter.DataTransformationFilter2;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.RenderableOp;
import javax.media.jai.RenderedOp;
import javax.media.jai.operator.ThresholdDescriptor;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import java.awt.image.renderable.RenderContext;
import java.awt.image.renderable.RenderableImage;
import java.security.InvalidParameterException;

public class ThresholdFilter extends DataTransformationFilter2<PlanarImage, PlanarImage> {

    public ThresholdFilter(Readable<PlanarImage> input, Writeable<PlanarImage> output) throws InvalidParameterException {
        super(input, output);
    }

    public ThresholdFilter(Readable<PlanarImage> input) throws InvalidParameterException {
        super(input);
    }

    public ThresholdFilter(Writeable<PlanarImage> output) throws InvalidParameterException {
        super(output);
    }


    @Override
    protected PlanarImage process(PlanarImage entity) {

        //Alles zwischen low und high wird weiß angezeigt (=constant)
        double[] low = new double[] { 0 }; //schwarz
        double[] high = new double[] { 30 };
        double[] constant = new double[] { 255 }; //weiß
        return ThresholdDescriptor.create(entity, low, high, constant, null);
    }
}
