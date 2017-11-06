package imaging.filter;

import pmp.filter.DataTransformationFilter2;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;

import javax.imageio.ImageIO;
import javax.media.jai.KernelJAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.RenderedOp;
import javax.media.jai.operator.DilateDescriptor;
import javax.media.jai.operator.ErodeDescriptor;
import java.awt.image.BufferedImage;
import java.io.File;
import java.security.InvalidParameterException;

public class SaveFilter extends DataTransformationFilter2<PlanarImage, PlanarImage> {

    public SaveFilter(Readable<PlanarImage> input, Writeable<PlanarImage> output) throws InvalidParameterException {
        super(input, output);
    }

    public SaveFilter(Readable<PlanarImage> input) throws InvalidParameterException {
        super(input);
    }

    public SaveFilter(Writeable<PlanarImage> output) throws InvalidParameterException {
        super(output);
    }

    @Override
    protected PlanarImage process(PlanarImage entity) {
        try {
            // retrieve image
            BufferedImage bi = entity.getAsBufferedImage();
            File outputfile = new File("Outputfiles\\LoetstellenFiltered.png");
            ImageIO.write(bi, "png", outputfile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }
}