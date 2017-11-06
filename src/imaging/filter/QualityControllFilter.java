package imaging.filter;

import CalcCentroidsFilterPkg.Coordinate;
import pmp.filter.DataTransformationFilter2;
import pmp.filter.Sink;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;

import javax.imageio.ImageIO;
import javax.media.jai.PlanarImage;
import java.awt.image.BufferedImage;
import java.io.*;
import java.security.InvalidParameterException;
import java.util.LinkedList;

public class QualityControllFilter extends Sink<LinkedList<Coordinate>> {

    int xTolerance;
    int yTolerance;

    public QualityControllFilter(int xTolerance, int yTolerance) throws InvalidParameterException {
        super();
        this.xTolerance = xTolerance;
        this.yTolerance = yTolerance;
    }

    public QualityControllFilter(int xTolerance, int yTolerance, Readable<LinkedList<Coordinate>> input) throws InvalidParameterException {
        super(input);
        this.xTolerance = xTolerance;
        this.yTolerance = yTolerance;
    }

    @Override
    public void write(LinkedList<Coordinate> coordinates) throws StreamCorruptedException {

        File outputFile = new File("Outputfiles", "tolerances.txt");

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile)))
        {
            bw.write("TEST");
        }
        catch(Exception e)
        {
        }
    }
}
