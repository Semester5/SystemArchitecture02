import CalcCentroidsFilterPkg.CalcCentroidsFilter;
import CalcCentroidsFilterPkg.Coordinate;
import imaging.filter.*;
import imaging.SourceReader;
import imaging.TestSink;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;
import pmp.pipes.SimplePipe;

import javax.media.jai.PlanarImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Christina on 30.10.2017.
 */
public class Main {
    public static void main(String[] args) {

        int x = 0;
        int y = 50;
        int width = 500;
        int height = 70;

        ArrayList<Coordinate> expectedCoordinates = new ArrayList<Coordinate>();
        expectedCoordinates.add(new Coordinate(10,80));
        expectedCoordinates.add(new Coordinate(73,77));
        expectedCoordinates.add(new Coordinate(110,80));
        expectedCoordinates.add(new Coordinate(202,80));
        expectedCoordinates.add(new Coordinate(265,79));
        expectedCoordinates.add(new Coordinate(330,81));
        expectedCoordinates.add(new Coordinate(396,81));

        int xTolerance = 2;
        int yTolerance = 2;

        String mode = "";
        while(true) {

            System.out.print("Möchten Sie das Programm im Push-Modus [push] oder im Pull-Modus [pull] starten?");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            try {
                mode = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if ("push".equals(mode.toLowerCase())) {
                QualityControllFilter qualityFilter = new QualityControllFilter(xTolerance, yTolerance, expectedCoordinates);

                SimplePipe<ArrayList<Coordinate>> pipeCalcCentroidsToQuality = new SimplePipe<>(qualityFilter);
                CalcCentroidsFilter calcCentroidsFilter = new CalcCentroidsFilter((Writeable) pipeCalcCentroidsToQuality);

                SimplePipe<PlanarImage> pipeSaveToCalcCentroids = new SimplePipe<>((Writeable<PlanarImage>) calcCentroidsFilter);
                SaveFilter saveFilter = new SaveFilter((Writeable) pipeSaveToCalcCentroids);

                SimplePipe<PlanarImage> pipeTest = new SimplePipe<>((Writeable<PlanarImage>) saveFilter);
                DisplayFilter displayFilter7 = new DisplayFilter((Writeable) pipeTest);

                SimplePipe<PlanarImage> pipeOpeningToDisplay = new SimplePipe<>((Writeable<PlanarImage>) displayFilter7);
                OpeningFilter openingFilter = new OpeningFilter((Writeable) pipeOpeningToDisplay);

                SimplePipe<PlanarImage> pipeDisplayToOpening = new SimplePipe<>((Writeable<PlanarImage>) openingFilter);
                DisplayFilter displayFilter3 = new DisplayFilter((Writeable) pipeDisplayToOpening);

                SimplePipe<PlanarImage> pipeMedianToDisplay = new SimplePipe<>((Writeable<PlanarImage>) displayFilter3);
                MedianFilter medianFilter = new MedianFilter((Writeable) pipeMedianToDisplay);

                SimplePipe<PlanarImage> pipeDisplayToMedian = new SimplePipe<>((Writeable<PlanarImage>) medianFilter);
                DisplayFilter displayFilter2 = new DisplayFilter((Writeable) pipeDisplayToMedian);

                SimplePipe<PlanarImage> pipeThresholdToDisplay = new SimplePipe<>((Writeable<PlanarImage>) displayFilter2);
                ThresholdFilter thresholdFilter = new ThresholdFilter((Writeable) pipeThresholdToDisplay);

                SimplePipe<PlanarImage> pipeROIToThreshold = new SimplePipe<>((Writeable<PlanarImage>) thresholdFilter);
                RegionOfInterestFilter regionOfInterestFilter = new RegionOfInterestFilter(x, y, width, height, (Writeable) pipeROIToThreshold);

                SimplePipe<PlanarImage> pipeDisplayToROI = new SimplePipe<>((Writeable<PlanarImage>) regionOfInterestFilter);
                DisplayFilter displayFilter1 = new DisplayFilter((Writeable) pipeDisplayToROI);

                SimplePipe<PlanarImage> pipeSourceToDisplay = new SimplePipe<>((Writeable<PlanarImage>) displayFilter1);
                SourceReader sourceReader = new SourceReader(pipeSourceToDisplay);

                sourceReader.run();
                return;
            } else if ("pull".equals(mode.toLowerCase())) {

                SourceReader sourceReader = new SourceReader();
                SimplePipe<PlanarImage> pipeROIFromSourceReader = new SimplePipe<>(sourceReader);

                RegionOfInterestFilter regionOfInterestFilter = new RegionOfInterestFilter(x, y, width, height, (Readable) pipeROIFromSourceReader);
                SimplePipe<PlanarImage> pipeThresholdFromRoi = new SimplePipe<>((Readable) regionOfInterestFilter);

                ThresholdFilter thresholdFilter = new ThresholdFilter((Readable) pipeThresholdFromRoi);
                SimplePipe<PlanarImage> pipeMedianFromTreshold = new SimplePipe<>((Readable) thresholdFilter);

                MedianFilter medianFilter = new MedianFilter((Readable) pipeMedianFromTreshold);
                SimplePipe<PlanarImage> pipeOpeningFromMedian = new SimplePipe<>((Readable) medianFilter);

                OpeningFilter openingFilter = new OpeningFilter((Readable) pipeOpeningFromMedian);
                SimplePipe<PlanarImage> pipeSaveFromOpening = new SimplePipe<>((Readable) openingFilter);

                SaveFilter saveFilter = new SaveFilter((Readable) pipeSaveFromOpening);
                SimplePipe<PlanarImage> pipeCalcCentroidsFromSave = new SimplePipe<>((Readable) saveFilter);

                CalcCentroidsFilter calcCentroidsFilter = new CalcCentroidsFilter((Readable) pipeCalcCentroidsFromSave);
                SimplePipe<ArrayList<Coordinate>> pipeQualityFromCalcCentroids = new SimplePipe<>((Readable)calcCentroidsFilter);

                QualityControllFilter qualityFilter = new QualityControllFilter(xTolerance, yTolerance, expectedCoordinates, pipeQualityFromCalcCentroids);
                qualityFilter.run();
                return;
            }
        }
    }
}