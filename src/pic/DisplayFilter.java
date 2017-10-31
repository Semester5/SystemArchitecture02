package pic;

import com.sun.media.jai.widget.DisplayJAI;
import pmp.filter.DataTransformationFilter2;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.swing.*;
import java.awt.*;
import java.security.InvalidParameterException;

/**
 * Created by Christina on 30.10.2017.
 */
public class DisplayFilter extends DataTransformationFilter2<PlanarImage, PlanarImage> {

    public DisplayFilter(Readable<PlanarImage> input, Writeable<PlanarImage> output) throws InvalidParameterException {
        super(input, output);
    }

    @Override
    protected PlanarImage process(PlanarImage entity) {
        // Create a frame for display.
        JFrame frame = new JFrame();
        frame.setTitle("DisplayJAI: loetstellen.jpg");

// Get the JFrame ContentPane.
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());


// Create an instance of DisplayJAI.
        DisplayJAI dj = new DisplayJAI(entity);


// Add to the JFrame ContentPane an instance of JScrollPane
// containing the DisplayJAI instance.
        contentPane.add(new JScrollPane(dj),BorderLayout.CENTER);

// Add a text label with the image information.
        //contentPane.add(new JLabel(imageInfo),BorderLayout.SOUTH);

// Set the closing operation so the application is finished.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,200); // adjust the frame size.
        frame.setVisible(true); // show the frame.
        return entity;
    }
}
