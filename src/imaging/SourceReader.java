package imaging;

import pmp.filter.Source;
import pmp.interfaces.Writeable;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;

/**
 * Created by Christina on 30.10.2017.
 */
public class SourceReader extends Source<PlanarImage>{

    public static final String INPUTFILE = "Inputfiles\\loetstellen.jpg";

    public SourceReader() {
    }

    public SourceReader(Writeable<PlanarImage> output) throws InvalidParameterException {
        super(output);
    }

    @Override
    public PlanarImage read() throws StreamCorruptedException {
        if(ENDING_SIGNAL == null) {
            PlanarImage image =  JAI.create("fileload", INPUTFILE);
            ENDING_SIGNAL = 1;
            return image;
        }
        return null;
    }
}
