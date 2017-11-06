package imaging.filter;

import pmp.filter.DataTransformationFilter2;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;

import javax.imageio.ImageIO;
import javax.media.jai.PlanarImage;
import java.awt.image.BufferedImage;
import java.io.File;
import java.security.InvalidParameterException;

public class QualityControllFilter extends DataTransformationFilter2<PlanarImage, PlanarImage> {
    public QualityControllFilter(Readable<PlanarImage> input, Writeable<PlanarImage> output) throws InvalidParameterException {
        super(input, output);
    }

    public QualityControllFilter(Readable<PlanarImage> input) throws InvalidParameterException {
        super(input);
    }

    public QualityControllFilter(Writeable<PlanarImage> output) throws InvalidParameterException {
        super(output);
    }

    @Override
    protected PlanarImage process(PlanarImage entity) {

        QualityControllFilter
        return null;
    }
}
