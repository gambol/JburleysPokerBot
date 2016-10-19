package main.Tests;

import main.ThreadController;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class takeScreenshot
{
    @Test
    public void takeScreenshot() throws Exception
    {

        //Set up an observer:
        Runnable observer0 = new ThreadController.OBSERVE_Screen();
        Thread Observer0 = new Thread(observer0);
        Observer0.start();
        try
        {
            //Debug: Give it a moment to set up
            Thread.sleep(1000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        //Capture the main screen:
        BufferedImage image = main.Tools.Generate.Screen.getBimage();

        String fileName = new SimpleDateFormat("yyyyMMddhhmm'.txt'").format(new Date());
        try
        {
            ImageIO.write(image, "PNG", new File("Tess3\\Visuals\\TestShots\\_crash_" + fileName + ".png"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }

}