package main.Tools.Scanners;

import main.Classes.ExternalClasses.ColorNameLookup;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class ScanPixelColour
{
    public static String scanPixelColour(int x, int y)
    {
        BufferedImage image = main.Tools.Generate.Screen.getBimage();

        int clr = 0;

        try
        {
            clr = image.getRGB(x, y);
        } catch (Exception e)
        {
        }

        int red = (clr & 0x00ff0000) >> 16;
        int green = (clr & 0x0000ff00) >> 8;
        int blue = clr & 0x000000ff;

        String suit = new ColorNameLookup().getColorNameFromRgb(red, green, blue);

        return suit;
    }

    public static String scanSuit(int x, int y)
    {
        BufferedImage image = main.Tools.Generate.Screen.getBimage();

        int clr = image.getRGB(x, y);

        int red = (clr & 0x00ff0000) >> 16;
        int green = (clr & 0x0000ff00) >> 8;
        int blue = clr & 0x000000ff;

        String suit;

        if ( red > 150 && green < 150 && blue < 150 )
        {
            suit = "h";
        }
        else if ( red < 110 && green > 140 && blue < 100 )
        {
            suit = "c";
        }
        else if ( blue > 160 )
        {
            suit = "d";
        }
        else
        {
            suit = "s";
        }

        return suit;
    }
}