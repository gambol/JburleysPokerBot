package main.Tools.Generate;

import main.Classes.ValidateCard;
import main.Tools.Scanners.ScanPixelColour;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_imgproc;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgproc.CV_TM_CCORR_NORMED;

public class cardGenMkII
{
    public static String run(int ex) //So...
    {
        String card;
        String suit;
        String beta = "";

        //This is the "Rapid Pixel Checker".
        String result = ScanPixelColour.scanPixelColour(248, 240); //<< this class grabs the pixel colour from the buffered image set by the observer thread we started earlier.

        //The colour for "no cards" in my poker client is described as "forest green". There is a whole colour chart to adjust this for your own client.
        if(!(result.equals("ForestGreen")))
        {
            // and here is the templatematch code
            card = cardGenTemplateMatch(ex); //< CTRL + Click here next!!
            suit = ScanPixelColour.scanSuit(Locations.eRekter(ex).x + 0, Locations.eRekter(ex).y + 0);

            if ( ValidateCard.run(card, suit) )
            {
                if ( !(card.isEmpty() && suit.isEmpty() && (card.length() == 2)) )
                {
                    beta = card + suit;
                }
            }
        }
        return beta;
    }

    public static String cardGenTemplateMatch(int ex)
    {
        // Okay! this is but isn't as bad as it looks. you can just let it run and do its thing.
        // Basically this is the template matching class that uses the JavaCV library.
        // So, what does this do? well...

        BufferedImage image = Screen.getBimage(); // < get the buffered Image set by the observer thread

        Rectangle r = main.Tools.Generate.Locations.eRekter(ex); //< This grabs the XY location and area size of whatever ID that was passed into this method,
                                                                 // head over to locations.java for a quick look and you will see.

                                                                 // Moving on...

        // Template matching requires a main image and a smaller image to compare to.
        // for simplicity the smaller image is called a "glyph" and the main image is the buffered image from the obserer class.

        // I will try to explain...

        //instantiate
        OpenCVFrameConverter.ToIplImage iplConverter = new OpenCVFrameConverter.ToIplImage();
        Java2DFrameConverter java2dConverter = new Java2DFrameConverter();

        BufferedImage grid= null;
        try
        {
            //load our template grid:
            grid = ImageIO.read(new File("Tess3\\Visuals\\research\\CoreVisuals\\ReferenceGrid.png"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        //so we need our main image to compare to, we need to process it like this:
        BufferedImage formatImage1 = new BufferedImage(grid.getWidth(),grid.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g2d1 = formatImage1.createGraphics();
        g2d1.drawImage(grid, 0, 0, null);
        g2d1.dispose();
        IplImage iplImage1 = iplConverter.convert(java2dConverter.convert(formatImage1));

        //same goes for our "crop" which is the smaller region of the main image, where you will find the data to match to our glyphs
        BufferedImage crop = image.getSubimage(r.x,r.y,r.width,r.height);
        BufferedImage formatImage2 = new BufferedImage(r.width,r.height, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g2d2 = formatImage2.createGraphics();
        g2d2.drawImage(crop, 0, 0, null);
        g2d2.dispose();
        IplImage iplImage2 = iplConverter.convert(java2dConverter.convert(formatImage2));

        opencv_core.IplImage grd = iplImage1;
        opencv_core.IplImage crp = iplImage2;

        //okay all formatted, now we match em' using this method
        opencv_core.IplImage result = cvCreateImage(cvSize(grd.width() - crp.width() + 1, grd.height() - crp.height() + 1), IPL_DEPTH_32F, 1);
        cvZero(result);

        opencv_imgproc.cvMatchTemplate(grd, crp, result, CV_TM_CCORR_NORMED);

        DoublePointer min_val = new DoublePointer();
        DoublePointer max_val = new DoublePointer();
        CvPoint minLoc = new CvPoint();
        CvPoint maxLoc = new CvPoint();

        cvMinMaxLoc(result, min_val, max_val, minLoc, maxLoc, null);

        CvPoint point = new CvPoint();
        point.x(maxLoc.x() + crp.width());
        point.y(maxLoc.y() + crp.height());

        opencv_imgproc.cvRectangle(grd, maxLoc, point, CvScalar.RED, 2, 8, 0);

        CvRect rect = new CvRect();
        rect.x(maxLoc.x());
        rect.y(maxLoc.y());

        //and here we go, the matching works, and brings back an XY value, it's saying
        //"hey we found the glyph at this XY"
        String ref = String.valueOf(maxLoc.x()+""+maxLoc.y());

        //So now we need to determine what we have found. Using the XY from the reference grid, and the positive match from the glyph, we can tell what card we are holding.
        switch (ref)
        {
            case "10":
            case "11":
                ref = "A";
                break;
            case "201":
            case "202":
                ref = "2";
                break;
            case "391":
            case "392":
                ref = "3";
                break;
            case "581":
            case "582":
                ref = "4";
                break;
            case "771":
            case "772":
                ref = "K";
                break;
            case "124":
            case "125":
                ref = "5";
                break;
            case "2024":
            case "2025":
                ref = "6";
                break;
            case "3924":
            case "3925":
                ref = "7";
                break;
            case "5824":
            case "5825":
                ref = "8";
                break;
            case "7723":
            case "7724":
                ref = "T";
                break;
            case "146":
            case "147":
                ref = "9";
                break;
            case "2047":
            case "2048":
                ref = "J";
                break;
            case "3947":
            case "3948":
                ref = "Q";
                break;
            default :
                ref = "";
                break;
        }
        return ref;
    }
}

// Done!
// Now you have an idea of how we generate the omega,
// you can understand how pot.java works, and set up should be a little easier to understand.

// The next step would be to take a look at the DecisionSystem.Java, it's where the "thinking" takes place.
// You may also want to alter the fastfold setting in the Thread Controller,

// Also, to reclibrate the chen you will need:
// main\Classes\ExternalClasses\Chen\Chen.java
// main\Tests\Run\ChenTesting.java

// Good luck! :)