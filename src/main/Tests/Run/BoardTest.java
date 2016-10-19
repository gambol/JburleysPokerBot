package main.Tests.Run;

import main.Classes.ValidateCard;
import main.Tools.Generate.Locations;
import main.Tools.Scanners.ScanPixelColour;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_imgproc;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_core.cvReleaseImage;
import static org.bytedeco.javacpp.opencv_highgui.cvShowImage;
import static org.bytedeco.javacpp.opencv_highgui.cvWaitKey;
import static org.bytedeco.javacpp.opencv_imgcodecs.cvLoadImage;
import static org.bytedeco.javacpp.opencv_imgproc.CV_TM_CCORR_NORMED;

public class BoardTest
{
    @Test
    public void cardGenMkII() throws Exception
    {
        main.Tests.setObserver.run();

        int ex = 1;
        String card = "";
        String suit;
        String beta = "";

        //Rapid Pixel Checker.
        String result = ScanPixelColour.scanPixelColour(248, 240);
        if(!(result.equals("ForestGreen")))
        {

            //templatematch code

            suit = ScanPixelColour.scanPixelColour(Locations.eRekter(ex).x + 16, Locations.eRekter(ex).y + 1);
            suit = main.Tools.Validators.cleanSuit.run(suit);

            if ( ValidateCard.run(card, suit) )
            {
                if ( !(card.isEmpty() && suit.isEmpty() && (card.length() == 2)) )
                {
                    beta = card + suit;
                }
            }
        }
        System.out.println(beta);
    }


    @Test
    public void cardGenTemplateMatch() throws Exception
    {
/*        main.Tests.setObserver.convertImage();

        //crop the card.
        BufferedImage image = Screen.getBimage();
        //Crop image
        Rectangle r = main.Tools.Generate.Locations.eRekter(0);
        BufferedImage crop = image.getSubimage(r.x,r.y,r.width,r.height);*/

        BufferedImage crop = ImageIO.read(new File("Tess3\\Visuals\\3523.png"));

        OpenCVFrameConverter.ToIplImage iplConverter = new OpenCVFrameConverter.ToIplImage();
        Java2DFrameConverter java2dConverter = new Java2DFrameConverter();
        IplImage iplImage = iplConverter.convert(java2dConverter.convert(crop));

        opencv_core.IplImage tmp = iplImage; //cvLoadImage("Tess3\\Visuals\\3523.png");// its the image from which we will find the region of interest
        opencv_core.IplImage src = cvLoadImage("Tess3\\ReferenceGrid.png");// here its the part of the image we want to match from the source image here we wanted to find the nose part

        opencv_core.IplImage result = cvCreateImage(cvSize(src.width() - tmp.width() + 1, src.height() - tmp.height() + 1), IPL_DEPTH_32F, 1);
        cvZero(result);
        opencv_imgproc.cvMatchTemplate(src, tmp, result, CV_TM_CCORR_NORMED); // fuction used to find or match the template from an image

        DoublePointer min_val = new DoublePointer();
        DoublePointer max_val = new DoublePointer();
        CvPoint minLoc = new CvPoint();
        CvPoint maxLoc = new CvPoint();

        cvMinMaxLoc(result, min_val, max_val, minLoc, maxLoc, null);// finding min and max location

        CvPoint point = new CvPoint();
        point.x(maxLoc.x() + tmp.width());
        point.y(maxLoc.y() + tmp.height());

        opencv_imgproc.cvRectangle(src, maxLoc, point, CvScalar.RED, 2, 8, 0);

        CvRect rect = new CvRect();
        rect.x(maxLoc.x());
        rect.y(maxLoc.y());

        System.out.println(maxLoc.x());
        System.out.println(maxLoc.y());

        String ref = String.valueOf(maxLoc.x()+""+maxLoc.y());

        switch (ref)
        {
            case "10":
                ref = "A";
                break;
            case "201":
                ref = "2";
                break;
            case "391":
                ref = "3";
                break;
            case "581":
                ref = "4";
                break;
            case "771":
                ref = "K";
                break;
            case "124":
                ref = "5";
                break;
            case "2024":
                ref = "6";
                break;
            case "3924":
                ref = "7";
                break;
            case "5824":
                ref = "8";
                break;
            case "7724":
            case "7723":
                ref = "T";
                break;
            case "147":
                ref = "9";
                break;
            case "2047":
                ref = "J";
                break;
            case "3947":
                ref = "Q";
                break;
            default :
                ref = "";
                break;
        }
        System.out.println(ref);
    }

    @Test
    public void stageGen() throws IOException
    {
        //main.Tests.setObserver.staticImage();

        int cardsOnTheBoard;
        String card;
        String bomega = "";

        for (int i = 0; i<7; i++)
        {
            card = main.Tools.Generate.cardGenMkII.cardGenTemplateMatch(i);
            System.out.println("Card: "+card);

            if (!(card.isEmpty()))
            {
                bomega = bomega+ card;
                System.out.println("Bomega: "+bomega);
            }
            else {
                break;
            }
        }

        cardsOnTheBoard = bomega.length();
        int stage = 0;

        if (cardsOnTheBoard == 2){
            stage = 1;
        }
        if (cardsOnTheBoard == 5){
            stage = 2;
        }
        if (cardsOnTheBoard == 6){
            stage = 3;
        }
        if (cardsOnTheBoard == 7){
            stage = 4;
        }

        System.out.println("STAGE: "+stage);
        main.Switchboard.setStage(stage);
        System.out.println(main.Switchboard.getStage());
    }
}