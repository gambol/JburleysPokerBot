package main.Classes;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class sysUpdate
{
    static String old;

    public static void msg(String news)
    {
        if (!(news.equals(old))){
            System.out.print("\n" + news);
            old = news;
        } else {
            //System.out.print(".");
        }
    }

    public static void takeScreenshot()
    {
        //Capture the main screen:
        BufferedImage image = main.Tools.Generate.Screen.getBimage();

        String fileName = new SimpleDateFormat("yyyyMMddhhmm'.txt'").format(new Date());
        try
        {
            ImageIO.write(image, "PNG", new File("Tess3\\Visuals\\TestShots\\" + fileName + ".png"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
