package main.Tests.Run;

import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_imgproc;
import org.junit.Test;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_highgui.cvShowImage;
import static org.bytedeco.javacpp.opencv_highgui.cvWaitKey;
import static org.bytedeco.javacpp.opencv_imgcodecs.cvLoadImage;
import static org.bytedeco.javacpp.opencv_imgproc.CV_TM_CCORR_NORMED;

public class TemplateMatchingTest
{
    @Test
    public void templateFullFunc() throws Exception
    {
        int width = 200;
        int height = 100;

        opencv_core.IplImage tmp = cvLoadImage("Tess3\\Visuals\\ImgDeckProcess\\352.png");// its the image from which we will find the region of interest
        opencv_core.IplImage src = cvLoadImage("Tess3\\ReferenceGrid.png");// here its the part of the image we want to match from the source image here we wanted to find the nose part

        //here it will show the result image same here if you get the assertion error change  valsrc.nChannels()
        opencv_core.IplImage result = cvCreateImage(cvSize(src.width() - tmp.width() + 1, src.height() - tmp.height() + 1), IPL_DEPTH_32F, 1);

        cvZero(result);

        // Match Template Function from OpenCV
        opencv_imgproc.cvMatchTemplate(src, tmp, result, CV_TM_CCORR_NORMED); // fuction used to find or match the template from an image

        // double[] min_val = new double[2];
        // double[] max_val = new double[2];
        DoublePointer min_val = new DoublePointer();
        DoublePointer max_val = new DoublePointer();

        CvPoint minLoc = new CvPoint();
        CvPoint maxLoc = new CvPoint();

        cvMinMaxLoc(result, min_val, max_val, minLoc, maxLoc, null);// finding min and max location

        CvPoint point = new CvPoint();
        point.x(maxLoc.x() + tmp.width());
        point.y(maxLoc.y() + tmp.height());
        // cvMinMaxLoc(src, min_val, max_val,0,0,result);

        // Draw a Rectangle for matched region
        opencv_imgproc.cvRectangle(src, maxLoc, point, CvScalar.RED, 2, 8, 0);

        CvRect rect = new CvRect();
        rect.x(maxLoc.x());
        rect.y(maxLoc.y());

        System.out.println(maxLoc.x());
        System.out.println(maxLoc.y());

        rect.width(tmp.width() + width);
        rect.height(tmp.width() + height);
        cvSetImageROI(src, rect);// here we are finding the region of interest from the source image
        IplImage imageNew = cvCreateImage(cvGetSize(src), src.depth(),
                src.nChannels());// after that we are creating the new image note that if you find the assertion error change the value of src.nChannels() to 1
        cvCopy(src, imageNew);
        //cvSaveImage(args[2], imageNew);

        cvShowImage("output image", src);// show the out put image in a window

        cvWaitKey(0);
        cvReleaseImage(src);
        cvReleaseImage(tmp);
        cvReleaseImage(result);// thats all from the coding part now lets see the image
    }

    @Test
    public void template() throws Exception
    {
        opencv_core.IplImage src = cvLoadImage("Tess3\\top.png");
        opencv_core.IplImage tmp = cvLoadImage("Tess3\\Visuals\\research\\CoreVisuals\\pot.png");

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
    }

    @Test
    public void templatetest() throws Exception
    {
        int width = 200;
        int height = 100;

        opencv_core.IplImage tmp = cvLoadImage("Tess3\\2.png");// its the image from which we will find the region of interest
        opencv_core.IplImage src = cvLoadImage("Tess3\\1.png");// here its the part of the image we want to match from the source image here we wanted to find the nose part

        //here it will show the result image same here if you get the assertion error change  valsrc.nChannels()
        opencv_core.IplImage result = cvCreateImage(cvSize(src.width() - tmp.width() + 1, src.height() - tmp.height() + 1), IPL_DEPTH_32F, 1);

        cvZero(result);

        // Match Template Function from OpenCV
        opencv_imgproc.cvMatchTemplate(src, tmp, result, CV_TM_CCORR_NORMED); // fuction used to find or match the template from an image

        // double[] min_val = new double[2];
        // double[] max_val = new double[2];
        DoublePointer min_val = new DoublePointer();
        DoublePointer max_val = new DoublePointer();

        CvPoint minLoc = new CvPoint();
        CvPoint maxLoc = new CvPoint();

        cvMinMaxLoc(result, min_val, max_val, minLoc, maxLoc, null);// finding min and max location

        CvPoint point = new CvPoint();
        point.x(maxLoc.x() + tmp.width());
        point.y(maxLoc.y() + tmp.height());
        // cvMinMaxLoc(src, min_val, max_val,0,0,result);

        // Draw a Rectangle for matched region
        opencv_imgproc.cvRectangle(src, maxLoc, point, CvScalar.RED, 2, 8, 0);

        CvRect rect = new CvRect();
        rect.x(maxLoc.x());
        rect.y(maxLoc.y());

        System.out.println(maxLoc.x());
        System.out.println(maxLoc.y());

        rect.width(tmp.width() + width);
        rect.height(tmp.width() + height);
        cvSetImageROI(src, rect);// here we are finding the region of interest from the source image
        IplImage imageNew = cvCreateImage(cvGetSize(src), src.depth(),
                src.nChannels());// after that we are creating the new image note that if you find the assertion error change the value of src.nChannels() to 1
        cvCopy(src, imageNew);
        //cvSaveImage(args[2], imageNew);

        cvShowImage("output image", src);// show the out put image in a window

        cvWaitKey(0);
        cvReleaseImage(src);
        cvReleaseImage(tmp);
        cvReleaseImage(result);// thats all from the coding part now lets see the image
    }

}