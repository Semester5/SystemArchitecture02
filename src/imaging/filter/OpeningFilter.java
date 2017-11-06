package imaging.filter;

import pmp.filter.DataTransformationFilter2;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;

import javax.media.jai.KernelJAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.RenderedOp;
import javax.media.jai.operator.ConvolveDescriptor;
import javax.media.jai.operator.DilateDescriptor;
import javax.media.jai.operator.ErodeDescriptor;
import javax.media.jai.operator.ThresholdDescriptor;
import java.security.InvalidParameterException;

public class OpeningFilter extends DataTransformationFilter2<PlanarImage, PlanarImage> {

    public OpeningFilter(Readable<PlanarImage> input, Writeable<PlanarImage> output) throws InvalidParameterException {
        super(input, output);
    }

    public OpeningFilter(Readable<PlanarImage> input) throws InvalidParameterException {
        super(input);
    }

    public OpeningFilter(Writeable<PlanarImage> output) throws InvalidParameterException {
        super(output);
    }

    @Override
    protected PlanarImage process(PlanarImage entity) {
        float[] kernelMatrix = new float[] {1, 1, 1, 1, 1,
                1, 1, 1, 1, 1,
                1, 1, 1, 1, 1,
                1, 1, 1, 1, 1,
                1, 1, 1, 1, 1};

        KernelJAI kernel = new KernelJAI(5, 5, kernelMatrix);
        RenderedOp renderedOp = ErodeDescriptor.create(entity, kernel, null);
        renderedOp = ErodeDescriptor.create(renderedOp , kernel, null);
        renderedOp = DilateDescriptor.create(renderedOp, kernel, null);
        renderedOp = DilateDescriptor.create(renderedOp, kernel, null);
        return renderedOp;
    }
}