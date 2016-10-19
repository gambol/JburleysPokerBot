package main.Tools.Generate;

import java.awt.image.BufferedImage;

public class Screen
{
    public static BufferedImage image;
    public static final Object tessLock = new Object();

    public static int screenX = 0;
    public static int screenY = 0;

    public static void setScreenX(int screenX){Screen.screenX = screenX;}
    public static void setScreenY(int screenY){Screen.screenY = screenY;}

    public static int getScreenX() {return screenX;}
    public static int getScreenY() {return screenY;}

    public static void setBimage(BufferedImage x)
    {
        synchronized (tessLock)
        {
            image = x;
        }
    }

    public static BufferedImage getBimage()
    {
        synchronized (tessLock)
        {
            return image;
        }
    }

}