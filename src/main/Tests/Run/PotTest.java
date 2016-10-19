package main.Tests.Run;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PotTest
{
    @Test
    public void cookieCutter() throws IOException
    {
        Rectangle r = new Rectangle(main.Tools.Generate.Locations.checkHalf(1));
        String artifact = null;

        BufferedImage image = null;
        //image = main.Tools.Generate.Screen.getBimage();
        image = ImageIO.read(new File("Tess3\\Visuals\\Roster\\x1\\roster.png"));
        //image = ImageIO.read(new File("Tess3\\pot.png"));

        int x = image.getMinX(); int y = image.getMinY(); int w = image.getWidth(); int h = image.getHeight();

        int wscale = 4;
        int hscale = 4;
        BufferedImage croppedImage = image.getSubimage(x, y, w, h);
        Image scaleImage = croppedImage.getScaledInstance(w*wscale, h*hscale, Image.SCALE_DEFAULT);
        BufferedImage formatImage = new BufferedImage(w*wscale, h*hscale, BufferedImage.SCALE_DEFAULT);
        Graphics2D g2d = formatImage.createGraphics();
        g2d.drawImage(scaleImage, 0, 0, null);
        g2d.dispose();

        ImageIO.write(formatImage, "PNG", new File("Tess3\\Visuals\\Roster\\x1\\roster4.png"));
        System.out.println("Result: " + artifact);

    }

    @Test
    public void scanPot2() throws Exception
    {
        String artifact;
        BufferedImage image = ImageIO.read(new File("Tess3\\Visuals\\research\\buttonRoster.png"));
        //BufferedImage image = main.Tools.Generate.Screen.getBimage();

        int scale = 4;
        Image scaleImage = image.getScaledInstance(image.getWidth()*scale/3, image.getHeight()*scale, Image.SCALE_SMOOTH);
        BufferedImage formatImage = new BufferedImage(image.getWidth()*scale/3, image.getHeight()*scale, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g2d = formatImage.createGraphics();
        g2d.drawImage(scaleImage, 0, 0, null);
        g2d.dispose();

        ITesseract tinstance = new Tesseract();
        tinstance.setDatapath("Tess3\\tessdata\\tessdata\\");
        artifact = tinstance.doOCR(formatImage);

        ImageIO.write(formatImage, "PNG", new File("Tess3\\RaiseRoster.png"));
        //Filter:
        //artifact = artifact.replaceAll("[^0123456789]", "");
        System.out.println(artifact);
    }
}