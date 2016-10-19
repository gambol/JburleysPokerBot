package main.Tests.Run;

import main.Classes.ValidateCard;
import main.Tools.Generate.Locations;
import main.Tools.Generate.Screen;
import main.Tools.Scanners.ScanPixelColour;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;

public class AreaTest
{

    @Test
    public void ReapTheScreenShots() throws Exception
    {
        try(Stream<Path> paths = Files.walk(Paths.get("Tess3\\Visuals\\TestShots"))) {
            paths.forEach(filePath ->
            {
                if ( Files.isRegularFile(filePath))
                {
                    System.out.println(filePath);

                    for (int i = 0; i < 1; i++)
                    {
                        Rectangle r = new Rectangle(main.Tools.Generate.Locations.eRekter(i));

                        int x = r.x; int y = r.y; int w = r.width; int h = r.height;

                        BufferedImage image = null;
                        try
                        {
                            image = ImageIO.read(new File(String.valueOf(filePath)));
                        } catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                        BufferedImage croppedImage = image.getSubimage(x, y, w, h);


                        File outputfile = new File("Tess3\\Visuals\\" +main.Tools.Generate.RandomRange.run(0,1000)+".png");
                        try
                        {
                            ImageIO.write(croppedImage, "png", outputfile);
                        } catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                    }

                }
            });
        }
    }

    @Test
    public void ImporveCaptureCardSpeedWithAbetterTessLibrary() throws Exception
    {
        long start = System.currentTimeMillis();
        int ex = 1;
        String card = "";
        String suit;
        String beta = "";

        BufferedImage image = Screen.getBimage();
        //Crop image
        Rectangle r = main.Tools.Generate.Locations.eRekter(ex);
        int x = r.x; int y = r.y; int w = r.width; int h = r.height;
        BufferedImage crop = image.getSubimage(x,y,w,h);

        ITesseract tinstance = new Tesseract();
        tinstance.setDatapath("Tess3\\tessdata\\");
        card = tinstance.doOCR(image, r);
        card = main.Tools.Validators.cleanCard.run(card);

        suit = ScanPixelColour.scanPixelColour(Locations.eRekter(ex).x + 16, Locations.eRekter(ex).y + 1);
        suit = main.Tools.Validators.cleanSuit.run(suit);

        if ( ValidateCard.run(card, suit) )
        {
            if ( !(card.isEmpty() && suit.isEmpty() && (card.length() == 2)) )
            {
                beta = card + suit;
            }
        }

        long elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println(beta + " found in :" +elapsedTimeMillis);
    }

    @Test
    public void Take_Screenshot() throws Exception
    {

        //main.Tests.setObserver.generate();

        //Capture the main screen:
        BufferedImage image = main.Tools.Generate.Screen.getBimage();

        String fileName = new SimpleDateFormat("yyyyMMddhhmm'.txt'").format(new Date());
        ImageIO.write(image, "PNG", new File("Tess3\\"+fileName+".png"));

    }

    @Test
    public void pointTest() throws Exception
    {
        for (int i = 0; i < 8; i++)
        {
            String output = ScanPixelColour.scanPixelColour(Locations.eRekter(i).x, Locations.eRekter(i).y);
            System.out.println(i + " :" + output);
        }
    }

    @Test
    public void scrapeAll() throws Exception
    {
        main.Tests.setObserver.staticImage();

        for (int i = 0; i < 11; i++)
        {
            Rectangle r = new Rectangle(main.Tools.Generate.Locations.eRekter(i));

            int x = r.x; int y = r.y; int w = r.width; int h = r.height;

            BufferedImage image = main.Tools.Generate.Screen.getBimage();
            BufferedImage croppedImage = image.getSubimage(x, y, w, h);

            File outputfile = new File("Tess3\\Visuals\\" + i + ".png");
            ImageIO.write(croppedImage, "png", outputfile);
        }
    }
}