package main.Classes;

import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_imgproc;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgcodecs.cvLoadImage;
import static org.bytedeco.javacpp.opencv_imgproc.CV_TM_CCORR_NORMED;

public class TemplateMatching
{
    public static int run() throws Exception
    {
        opencv_core.IplImage src = cvLoadImage("Tess3\\top.png");
        opencv_core.IplImage tmp = cvLoadImage("Tess3\\Visuals\\research\\CoreVisuals\\pot.png");

        opencv_core.IplImage result = cvCreateImage(cvSize(src.width() - tmp.width() + 1, src.height() - tmp.height() + 1), IPL_DEPTH_32F, 1);
        cvZero(result);
        opencv_imgproc.cvMatchTemplate(src, tmp, result, CV_TM_CCORR_NORMED);

        DoublePointer min_val = new DoublePointer();
        DoublePointer max_val = new DoublePointer();
        CvPoint minLoc = new CvPoint();
        CvPoint maxLoc = new CvPoint();

        cvMinMaxLoc(result, min_val, max_val, minLoc, maxLoc, null);

        CvPoint point = new CvPoint();
        point.x(maxLoc.x() + tmp.width());
        point.y(maxLoc.y() + tmp.height());

        opencv_imgproc.cvRectangle(src, maxLoc, point, CvScalar.RED, 2, 8, 0);

        CvRect rect = new CvRect();
        rect.x(maxLoc.x());
        rect.y(maxLoc.y());

        return maxLoc.x()+tmp.width();
    }
}
