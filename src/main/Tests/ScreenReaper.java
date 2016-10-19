package main.Tests;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ScreenReaper
{
    @Test
    public void ReapTheScreenShots() throws IOException
    {
        try(Stream<Path> paths = Files.walk(Paths.get("Tess3\\Visuals\\TestShots"))) {
            paths.forEach(filePath ->
            {
                if ( Files.isRegularFile(filePath))
                {
                    System.out.println(filePath);

                    for (int i = 11; i == 11; i++)
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
                        int scale = 3;
                        Image scaleImage = croppedImage.getScaledInstance(croppedImage.getWidth()*scale, croppedImage.getHeight()*scale, Image.SCALE_AREA_AVERAGING);
                        BufferedImage formatImage = new BufferedImage(croppedImage.getWidth()*scale, croppedImage.getHeight()*scale, BufferedImage.SCALE_DEFAULT);
                        Graphics2D g2d = formatImage.createGraphics();
                        g2d.drawImage(scaleImage, 0, 0, null);
                        g2d.dispose();


                        File outputfile = new File("Tess3\\Visuals\\" +main.Tools.Generate.RandomRange.run(0,1000)+".png");
                        try
                        {
                            ImageIO.write(formatImage, "png", outputfile);
                        } catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                    }

                }
            });
        }
    }
}
