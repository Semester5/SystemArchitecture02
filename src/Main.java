import imaging.filter.DisplayFilter;
import imaging.SourceReader;
import imaging.TestSink;
import imaging.filter.RegionOfInterestFilter;
import pmp.interfaces.Writeable;
import pmp.pipes.SimplePipe;

import javax.media.jai.PlanarImage;

/**
 * Created by Christina on 30.10.2017.
 */
public class Main {
    public static void main(String[] args) {
        SourceReader source =
            new SourceReader(
                new SimplePipe<PlanarImage>(
                    (Writeable<PlanarImage>) new DisplayFilter(
                        new SimplePipe<PlanarImage>(
                            new SourceReader()
                        ),
                        new SimplePipe<PlanarImage>(
                            (Writeable<PlanarImage>) new RegionOfInterestFilter(
                                new SimplePipe<PlanarImage>(
                                    new SourceReader()
                                ),
                                new SimplePipe<PlanarImage>(
                                    (Writeable<PlanarImage>) new DisplayFilter(
                                        new SimplePipe<PlanarImage>(
                                                new SourceReader()
                                        ),
                                        new SimplePipe<PlanarImage>(
                                                new TestSink()
                                        )
                                    )
                                )
                            )
                        )
                    )
                )
            );

        source.run();
    }
}