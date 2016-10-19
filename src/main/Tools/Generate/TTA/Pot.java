package main.Tools.Generate.TTA;

import main.Tools.Generate.Screen;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.opencv_imgproc;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import java.util.Arrays;
import java.util.List;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgproc.CV_TM_CCORR_NORMED;

public class Pot
{
    private static int outputx;
    private static int outputy;
    private static Double match;

    public static void run()
    {
        try
        {
            main();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static void main() throws Exception
    {
        //Button value x3 in scale:
        BufferedImage newGrid = scrapexIII();

        //Glyphs:
        BufferedImage zero  = ImageIO.read(new File("Tess3\\Visuals\\research\\CoreVisuals\\Pot\\0.png"));
        BufferedImage one   = ImageIO.read(new File("Tess3\\Visuals\\research\\CoreVisuals\\Pot\\1.png"));
        BufferedImage two   = ImageIO.read(new File("Tess3\\Visuals\\research\\CoreVisuals\\Pot\\2.png"));
        BufferedImage three = ImageIO.read(new File("Tess3\\Visuals\\research\\CoreVisuals\\Pot\\3.png"));
        BufferedImage four  = ImageIO.read(new File("Tess3\\Visuals\\research\\CoreVisuals\\Pot\\4.png"));
        BufferedImage five  = ImageIO.read(new File("Tess3\\Visuals\\research\\CoreVisuals\\Pot\\5.png"));
        BufferedImage six   = ImageIO.read(new File("Tess3\\Visuals\\research\\CoreVisuals\\Pot\\6.png"));
        BufferedImage seven = ImageIO.read(new File("Tess3\\Visuals\\research\\CoreVisuals\\Pot\\7.png"));
        BufferedImage eight = ImageIO.read(new File("Tess3\\Visuals\\research\\CoreVisuals\\Pot\\8.png"));
        BufferedImage nine  = ImageIO.read(new File("Tess3\\Visuals\\research\\CoreVisuals\\Pot\\9.png"));
        BufferedImage p     = ImageIO.read(new File("Tess3\\Visuals\\research\\CoreVisuals\\Pot\\p.png"));

        List<BufferedImage> glyph = new LinkedList <>();
        glyph.add(zero);
        glyph.add(one);
        glyph.add(two);
        glyph.add(three);
        glyph.add(four);
        glyph.add(five);
        glyph.add(six);
        glyph.add(seven);
        glyph.add(eight);
        glyph.add(nine);
        glyph.add(p);

        List<ArrayList<Integer>> Unit_XY_and_number = new ArrayList <>();

        for(int i = 0; i<11;)
        {
            BufferedImage n = glyph.get(i);
            convertImage(n, newGrid);

            if ( match > 0.99 )
            {
/*                System.out.println(i +"::: Match % " + match + " Match Found.");
                System.out.println("x " + outputx);
                System.out.println("y " + outputy);*/

                Unit_XY_and_number.add(new ArrayList <>(Arrays.asList(outputx, i)));

                //Blackout text
                int x = outputx;
                int y = outputy;
                Graphics2D graph = newGrid.createGraphics();
                graph.setColor(Color.BLACK);
                graph.fill(new Rectangle(x, y, n.getWidth(), n.getHeight()));
                graph.dispose();
                ImageIO.write(newGrid, "png", new File("Tess3\\Visuals\\research\\CoreVisuals\\Pot\\gridShot.png"));
            }
            else
            {
                //System.out.println(match + " No match.");
                i++;
            }
        }

        Collections.sort(Unit_XY_and_number, (o1, o2) -> o1.get(0).compareTo(o2.get(0)));

        //System.out.println(Unit_XY_and_number);
        int x = Unit_XY_and_number.size();

        String artifact = "";

        for (int i = 0; i <x; i++)
        {
            if (Unit_XY_and_number.get(i).get(1)==10){
                artifact = artifact + ".";
            } else {
                artifact = artifact +Unit_XY_and_number.get(i).get(1);
            }
        }

        if(artifact.isEmpty())
        {
            artifact = "0";
        }

        double convert = Double.parseDouble(artifact);

       // System.out.println("DEBUG: Pot: "+convert);
        main.Switchboard.setPot(convert);

    }

    private static BufferedImage scrapexIII()
    {
            BufferedImage image  = Screen.getBimage();
            Rectangle r = main.Tools.Generate.Locations.pot(0);
            int x = r.x; int y = r.y; int w = r.width; int h = r.height;
            BufferedImage croppedImage = image.getSubimage(x,y,w,h);

            //Scale and format
            int scale = 3;
            Image scaleImage = croppedImage.getScaledInstance(croppedImage.getWidth()*scale, croppedImage.getHeight()*scale, Image.SCALE_AREA_AVERAGING);
            BufferedImage formatImage = new BufferedImage(croppedImage.getWidth()*scale, croppedImage.getHeight()*scale, BufferedImage.SCALE_DEFAULT);
            Graphics2D g2d = formatImage.createGraphics();
            g2d.drawImage(scaleImage, 0, 0, null);
            g2d.dispose();

        return formatImage;
    }

    private static void convertImage(BufferedImage glyph, BufferedImage grid) throws Exception
    {
        OpenCVFrameConverter.ToIplImage iplConverter = new OpenCVFrameConverter.ToIplImage();
        Java2DFrameConverter java2dConverter = new Java2DFrameConverter();

        BufferedImage formatGlyph = new BufferedImage(glyph.getWidth(),glyph.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g2d2 = formatGlyph.createGraphics();
        g2d2.drawImage(glyph, 0, 0, null);
        g2d2.dispose();
        IplImage iplImageGlyph = iplConverter.convert(java2dConverter.convert(formatGlyph));

        BufferedImage formatGrid = new BufferedImage(grid.getWidth(),grid.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g2d1 = formatGrid.createGraphics();
        g2d1.drawImage(grid, 0, 0, null);
        g2d1.dispose();
        IplImage iplImageGrid = iplConverter.convert(java2dConverter.convert(formatGrid));

        template(iplImageGlyph, iplImageGrid);
    }

    private static void template(IplImage glf, IplImage grd) throws Exception
    {
        IplImage glyph = glf;
        IplImage grid = grd;

        IplImage result = cvCreateImage(cvSize(grid.width() - glyph.width() + 1, grid.height() - glyph.height() + 1), IPL_DEPTH_32F, 1);
        cvZero(result);
        opencv_imgproc.cvMatchTemplate(grid, glyph, result, CV_TM_CCORR_NORMED);

        double[] min_val1 = new double[2];
        double[] max_val1 = new double[2];
        cvMinMaxLoc(result, min_val1, max_val1);
        match = max_val1[0];

        DoublePointer min_val = new DoublePointer();
        DoublePointer max_val = new DoublePointer();
        CvPoint minLoc = new CvPoint();
        CvPoint maxLoc = new CvPoint();
        cvMinMaxLoc(result, min_val, max_val, minLoc, maxLoc, null);
        outputx = maxLoc.x();
        outputy = maxLoc.y();
    }
}


