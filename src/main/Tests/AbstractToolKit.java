package main.Tests;

import main.ThreadController;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

// COPY AND PASTE FROM HERE TO CREATE NEW TESTING TOOLS.

public class AbstractToolKit
{
    @Test
    public void CaptureLiveScreen() throws IOException
    {
        //Set up an observer:
        Runnable observer0 = new ThreadController.OBSERVE_Screen(); Thread Observer0 = new Thread(observer0);
        Observer0.start();
        //Debug: Give it a moment to set up
        try{ Thread.sleep(1000); } catch (InterruptedException e){e.printStackTrace();}

        //Capture the main screen:
        BufferedImage image = main.Tools.Generate.Screen.getBimage();

        //Save Image
        String fileName = new SimpleDateFormat("yyyyMMddhhmm'.txt'").format(new Date());
        ImageIO.write(image, "PNG", new File("Tess3\\"+fileName+".png"));

        //Run OCR
        //String artifact = main.Tools.Generate.Screen.scanOCR(image);
        //System.out.println("Result: " + artifact);
    }

    @Test
    public void CaptureLiveScreenSegment() throws IOException
    {
        //Set up an observer:
        Runnable observer0     = new ThreadController.OBSERVE_Screen(); Thread Observer0 = new Thread(observer0);
        Observer0.start();
        //Debug: Give it a moment to set up
        try{ Thread.sleep(1000); } catch (InterruptedException e){e.printStackTrace();}

        //Area to check:
        Rectangle r = main.Tools.Generate.Locations.checkHalf(1);
        int x = r.x; int y = r.y; int w = r.width; int h = r.height;

        //Crop main screen:
        BufferedImage croppedImage = main.Tools.Generate.Screen.getBimage().getSubimage(x,y,w,h);

        //Save crop
        String fileName = new SimpleDateFormat("yyyyMMddhhmm'.txt'").format(new Date());
        //Option 1
        ImageIO.write(croppedImage, "PNG", new File("Tess3\\"+fileName+".png"));
        //Option 2
        //ImageIO.write(croppedImage, "PNG", new File("Tess3\\main.png"));
        //Option 3
        //ImageIO.write(croppedImage, "PNG", new File("Tess3\\pot.png"));

        //Run OCR on crop:
        //String result = main.Tools.Generate.Screen.scanOCR(croppedImage);

        //Print:
        //System.out.println(result);
    }
}