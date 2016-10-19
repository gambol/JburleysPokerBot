package main.Tools.Generate;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Locations
{
    //  RECTANGLE
    static Rectangle cardZone1 = new Rectangle(218, 240, 18, 22);
    static Rectangle cardZone2 = new Rectangle(248, 240, 18, 22);
    static Rectangle flopZone1 = new Rectangle(167, 158, 18, 22);
    static Rectangle flopZone2 = new Rectangle(199, 158, 18, 22);
    static Rectangle flopZone3 = new Rectangle(231, 158, 18, 22);
    static Rectangle turnZone = new Rectangle(263, 158, 18, 22);
    static Rectangle riverZone = new Rectangle(295, 158, 18, 22);
    static Rectangle foldbtnArea = new Rectangle(252, 330, 33, 18);
    static Rectangle checkbtnArea = new Rectangle(316, 327, 69, 27);
    static Rectangle raisebtnArea = new Rectangle(398, 327, 69, 27);
    static Rectangle raiseboxArea = new Rectangle(343, 309, 29, 14);
    static Rectangle theTopCheckHalf = new Rectangle(215, 326, 72, 15);
    static Rectangle theBottomCheckHalf = new Rectangle(314, 340, 72, 15);
    static Rectangle topPotArea = new Rectangle(183, 126, 106, 17);
    static Rectangle midScreenRaiseData = new Rectangle(187, 199, 118, 13);

    //  SPRITE GAP
    static Point gap1 = new Point(236, 234);
    static Point gap2 = new Point(35, 163);

    //  X Y COORDINATES(POINTS)
    static Point enemyCardPoint1 = new Point(91, 234);
    static Point enemyCardPoint2 = new Point(35, 163);
    static Point enemyCardPoint3 = new Point(65, 94);
    static Point enemyCardPoint4 = new Point(157, 64);
    static Point enemyCardPoint5 = new Point(300, 64);
    static Point enemyCardPoint6 = new Point(390, 96);
    static Point enemyCardPoint7 = new Point(417, 162);
    static Point enemyCardPoint8 = new Point(363, 232);
    static Point cntrFoldbtn = new Point(266, 342);
    static Point cntrCheckbtn = new Point(347, 341);
    static Point cntrRaisebtn = new Point(431, 342);
    static Point cntrRaisebox = new Point(356, 315);

    // See? XY points are up there ^
    public static Rectangle eRekter(int number)
    {
        //the array to grab them here. Simple stuff. Press ALT+left key to go back to the cardGenMKII and we will continue.
        List <Rectangle> rects = new LinkedList <>();
        rects.add(cardZone1);
        rects.add(cardZone2);
        rects.add(flopZone1);
        rects.add(flopZone2);
        rects.add(flopZone3);
        rects.add(turnZone);
        rects.add(riverZone);
        rects.add(foldbtnArea);
        rects.add(checkbtnArea);
        rects.add(raisebtnArea);
        rects.add(raiseboxArea);
        rects.add(topPotArea);
        Rectangle x = rects.get(number);
        return x;
    }

    public static Point pRekter(int number)
    {
        List <Point> rects = new LinkedList <>();
        rects.add(enemyCardPoint1);
        rects.add(enemyCardPoint2);
        rects.add(enemyCardPoint3);
        rects.add(enemyCardPoint4);
        rects.add(enemyCardPoint5);
        rects.add(enemyCardPoint6);
        rects.add(enemyCardPoint7);
        rects.add(enemyCardPoint8);
        rects.add(cntrFoldbtn);
        rects.add(cntrCheckbtn);
        rects.add(cntrRaisebtn);
        rects.add(cntrRaisebox);

        Point x = rects.get(number);

        return x;
    }

    public static Point gap(int number)
    {
        List <Point> gap = new LinkedList <>();
        gap.add(gap1);
        gap.add(gap2);

        Point x = gap.get(number);

        return x;
    }

    static Rectangle ffArea = new Rectangle(234, 232, 71, 23);
    public static Rectangle crf(int number)
    {
        List <Rectangle> crf = new LinkedList <>();
        crf.add(checkbtnArea);
        crf.add(raisebtnArea);
        crf.add(foldbtnArea);
        crf.add(ffArea);

        Rectangle x = crf.get(number);

        return x;
    }

    static Point ffp = new Point(265, 343);
    public static Point crfp(int number)
    {
        List <Point> crfpoi = new LinkedList <>();
        crfpoi.add(cntrFoldbtn);
        crfpoi.add(cntrCheckbtn);
        crfpoi.add(cntrRaisebtn);
        crfpoi.add(cntrRaisebox);
        crfpoi.add(ffp);

        Point x = crfpoi.get(number);

        return x;
    }

    public static Rectangle checkHalf(int number)
    {
        List <Rectangle> check = new LinkedList <>();
        check.add(theTopCheckHalf);
        check.add(theBottomCheckHalf);
        check.add(topPotArea);
        Rectangle x = check.get(number);
        return x;
    }

    public static Rectangle pot(int number)
    {
        List <Rectangle> pot = new LinkedList <>();
        pot.add(topPotArea);
        pot.add(midScreenRaiseData);
        Rectangle x = pot.get(number);
        return x;
    }

    //Dealer Positions
    static Point dealerPos0R = new Point(286,240);
    static Point dealerPos1R = new Point(161,235);
    static Point dealerPos2R = new Point(99,165);
//  static Point dealerPos2R = new Point(114,140);
//  static Point dealerPos2W = new Point(117,112);

    public static Point dealerPos(int number)
    {
        List <Point> dealer = new LinkedList <>();
        dealer.add(dealerPos0R);
        dealer.add(dealerPos1R);
        dealer.add(dealerPos2R);

        Point x = dealer.get(number);

        return x;
    }

}