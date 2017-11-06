import imaging.filter.DisplayFilter;
import imaging.SourceReader;
import imaging.TestSink;
import imaging.filter.MedianFilter;
import imaging.filter.RegionOfInterestFilter;
import imaging.filter.ThresholdFilter;
import pmp.interfaces.Writeable;
import pmp.pipes.SimplePipe;

import javax.media.jai.PlanarImage;
import javax.media.jai.operator.ThresholdDescriptor;

/**
 * Created by Christina on 30.10.2017.
 */
public class Main {
    public static void main(String[] args) {

        int x = 0;
        int y = 50;
        int width = 500;
        int height = 70;

        TestSink testSink = new TestSink();
        SimplePipe<PlanarImage> pipeDisplayToSink = new SimplePipe<PlanarImage>(testSink);
        DisplayFilter displayFilter3 = new DisplayFilter((Writeable) pipeDisplayToSink);

        SimplePipe<PlanarImage> pipeMedianToDisplay = new SimplePipe<>((Writeable<PlanarImage>) displayFilter3);
        MedianFilter medianFilter = new MedianFilter((Writeable) pipeMedianToDisplay);

        SimplePipe<PlanarImage> pipeDisplayToMedian = new SimplePipe<>((Writeable<PlanarImage>) medianFilter);
        DisplayFilter displayFilter2 = new DisplayFilter((Writeable) pipeDisplayToMedian);

        SimplePipe<PlanarImage> pipeThresholdToDisplay = new SimplePipe<>((Writeable<PlanarImage>) displayFilter2);
        ThresholdFilter thresholdFilter = new ThresholdFilter((Writeable) pipeThresholdToDisplay);

        SimplePipe<PlanarImage> pipeROIToThreshold = new SimplePipe<>((Writeable<PlanarImage>) thresholdFilter);
        RegionOfInterestFilter regionOfInterestFilter = new RegionOfInterestFilter(x, y, width, height, (Writeable)  pipeROIToThreshold);

        SimplePipe<PlanarImage> pipeDisplayToROI = new SimplePipe<>((Writeable<PlanarImage>) regionOfInterestFilter);
        DisplayFilter displayFilter1 = new DisplayFilter((Writeable) pipeDisplayToROI);

        SimplePipe<PlanarImage> pipeSourceToDisplay = new SimplePipe<>((Writeable<PlanarImage>) displayFilter1);
        SourceReader sourceReader = new SourceReader(pipeSourceToDisplay);

        sourceReader.run();
    }
}