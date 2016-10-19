package main.Tests;

import main.ThreadController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class setObserver
{
    public static void run()
    {
        //Set up an observer:
        Runnable observer0 = new ThreadController.OBSERVE_Screen(); Thread Observer0 = new Thread(observer0);
        Observer0.start();
        //Debug: Give it a moment to set up
        try{ Thread.sleep(1000); } catch (InterruptedException e){e.printStackTrace();}
    }

    public static void staticImage()
    {
        BufferedImage image = null;
        try
        {
            image = ImageIO.read(new File("Tess3\\Visuals\\main.png"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        main.Tools.Generate.Screen.setBimage(image);
    }
}
