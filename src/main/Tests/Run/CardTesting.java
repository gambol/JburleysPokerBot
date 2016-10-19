package main.Tests.Run;

import main.Classes.ExternalClasses.ColorNameLookup;
import main.Tools.Generate.cardGenMkII;
import main.Tools.Scanners.ScanPixelColour;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CardTesting
{
    @Test
    public void cardGenMkII() throws Exception
    {
        main.Tests.setObserver.staticImage();
        cardGenMkII ii = new cardGenMkII();
        System.out.println(ii.run(0));
        System.out.println(ii.run(1));
    }

    @Test
    public void scanCardColour() throws Exception
    {
        main.Tests.setObserver.staticImage();
        String result = ScanPixelColour.scanPixelColour(248, 240);
        System.out.println(result);
    }

    @Test
    public void scanPixelofCard() throws IOException
    {

        BufferedImage c = ImageIO.read(new File("Tess3\\Visuals\\c.png"));
        BufferedImage s = ImageIO.read(new File("Tess3\\Visuals\\s.png"));

        //and so the GAP was made
        int x = 236;
        int y = 231;

        int clr = c.getRGB(x , y);
        int red = (clr & 0x00ff0000) >> 16;
        int green = (clr & 0x0000ff00) >> 8;
        int blue = clr & 0x000000ff;
        String suit = new ColorNameLookup().getColorNameFromRgb(red, green, blue);
        System.out.println(suit);
        ////
    }
}